package org.joget.marketplace;

import org.joget.apps.app.service.AppUtil;

public class ParseAppVariableHashVariable extends ParseEnvironmentVariableHashVariable{
    private static final String MESSAGE_PATH = "messages/parseEnvironmentVariableHashVariable";
    
    @Override
    public String getName() {
        return "Parse App Variable Hash Variable";
    }

    @Override
    public String getPrefix() {
        return "appVariableParse";
    }
    
    @Override
    public String getPropertyAssistantDefinition() {
        return AppUtil.readPluginResource(getClass().getName(), "/properties/assist/ParseAppVariableHashVariable.json", null, true, MESSAGE_PATH);
    }
}
