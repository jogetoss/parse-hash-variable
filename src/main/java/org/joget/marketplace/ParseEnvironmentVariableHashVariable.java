package org.joget.marketplace;

import java.util.ArrayList;
import java.util.Collection;
import org.joget.apps.app.dao.EnvironmentVariableDao;
import org.joget.apps.app.model.AppDefinition;
import org.joget.apps.app.model.DefaultHashVariablePlugin;
import org.joget.apps.app.model.EnvironmentVariable;
import org.joget.apps.app.service.AppUtil;
import org.joget.commons.util.LogUtil;
import org.joget.workflow.model.WorkflowAssignment;
import org.springframework.context.ApplicationContext;

public class ParseEnvironmentVariableHashVariable extends DefaultHashVariablePlugin {
    
    private static final String MESSAGE_PATH = "messages/parseEnvironmentVariableHashVariable";

    @Override
    public String processHashVariable(String variableKey) {
        try {
            String environmentVariableKey = variableKey;
            
            //first check and retrieve parameters passed in with URL query parameters syntax wrapped in square bracket []
            String queryParams = null;
            if (variableKey.contains("[") && variableKey.contains("]")) {
                queryParams = variableKey.substring(variableKey.indexOf("[") + 1, variableKey.indexOf("]"));
                environmentVariableKey = variableKey.substring(0, variableKey.indexOf("["));
                
//                //should not run as some hash variable in queryParams is not parsed yet
//                if (queryParams.contains("?") || (queryParams.contains("{") && queryParams.contains("}"))) {
//                    return null;
//                }
            }
//
//            //Parse the query parameters to a map
//            Map<String, String[]> parameters = null;
//            if (queryParams != null && !queryParams.isEmpty()) {
//                parameters = StringUtil.getUrlParams(queryParams);
//                
//                //put all parameters to plugin properties
//                getProperties().putAll(parameters);
//            }

            //Retrieve the enviroment variable based on environmentVariableKey
            AppDefinition appDef = (AppDefinition) getProperty("appDefinition");
            if (appDef != null) {
                ApplicationContext appContext = AppUtil.getApplicationContext();
                EnvironmentVariableDao environmentVariableDao = (EnvironmentVariableDao) appContext.getBean("environmentVariableDao");
                EnvironmentVariable env = environmentVariableDao.loadById(environmentVariableKey, appDef);
                if (env != null) {
                    String script = env.getValue();
                    script = AppUtil.processHashVariable(script, (WorkflowAssignment) getProperty("workflowAssignment"), null, null, appDef);

                    //execute the script with all plugin properties
                    //return executeScript(script, getProperties());
                    return script;
                } else {
                    //environment variable not found, return empty value
                    return "";
                }
            }
        } catch (Exception e) {
            //log the exception using LogUtil
            LogUtil.error(getClassName(), e, "#beanshell."+variableKey+"# fail to parse.");
        }
        
        //return null to by pass the replacing
        return null;
    }

    public String getName() {
        return "Parse Environment Variable Hash Variable";
    }

    public String getPrefix() {
        return "envVariableParse";
    }

    public String getVersion() {
        return Activator.PLUGIN_VERSION;
    }

    public String getDescription() {
        return "";
    }

    public String getLabel() {
        return "Parse Environment Variable Hash Variable";
    }

    public String getClassName() {
        return this.getClass().getName();
    }

    public String getPropertyOptions() {
        return "";
    }

    @Override
    public String getPropertyAssistantDefinition() {
        return AppUtil.readPluginResource(getClass().getName(), "/properties/assist/parseEnvironmentVariableHashVariable.json", null, true, MESSAGE_PATH);
    }

    @Override
    public Collection<String> availableSyntax() {
        Collection<String> syntax = new ArrayList<String>();
        syntax.add("envVariableParse.key");
        return syntax;
    }
    
    
}
