package org.joget.marketplace;

import java.util.ArrayList;
import java.util.Collection;
import org.joget.apps.app.lib.FormHashVariable;
import org.joget.apps.app.model.AppDefinition;
import org.joget.apps.app.service.AppUtil;
import org.joget.workflow.model.WorkflowAssignment;

public class ParseFormHashVariable extends FormHashVariable{
    private static final String MESSAGE_PATH = "messages/parseFormHashVariable";
    
    public String getName() {
        return "Form Data Hash Variable with parsing";
    }
    
    public String getVersion() {
        return Activator.PLUGIN_VERSION;
    }
    
    public String getPrefix() {
        return "formParse";
    }

    public String getDescription() {
        return "Return parsed form data hash variable and without converting character # into html entity";
    }

    public String getLabel() {
        return "Parse Form Data Hash Variable";
    }
    
    @Override
    public Collection<String> availableSyntax() {
        Collection<String> syntax = new ArrayList<String>();
        syntax.add("formParse.TABLE.COLUMN");
        syntax.add("formParse.TABLE.COLUMN[PRIMARY_KEY]");
        
        return syntax;
    }
    @Override
    public String processHashVariable(String variableKey) {
        String content = super.processHashVariable(variableKey);
        
        AppDefinition appDef = (AppDefinition) getProperty("appDefinition");
        if (appDef != null && content != null) {
            content = AppUtil.processHashVariable(content, (WorkflowAssignment) getProperty("workflowAssignment"), null, null, appDef);
        }
        return content;
    }
    
    @Override
    public String getPropertyAssistantDefinition() {
        return AppUtil.readPluginResource(getClass().getName(), "/properties/assist/parseFormHashVariable.json", null, true, MESSAGE_PATH);
    }
    
    @Override
    public String escapeHashVariableValue(String value) {
        //return AppUtil.escapeHashVariable(value);
        value = value.replaceAll("&#35;", "#");
        return value;
    }
}
