package org.joget.marketplace;

import java.util.ArrayList;
import java.util.Collection;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

    protected Collection<ServiceRegistration> registrationList;
    public static final String PLUGIN_VERSION = "8.0.0";

    public void start(BundleContext context) {
        registrationList = new ArrayList<ServiceRegistration>();

        //Register plugin here
        registrationList.add(context.registerService(ParseEnvironmentVariableHashVariable.class.getName(), new ParseEnvironmentVariableHashVariable(), null));
        registrationList.add(context.registerService(ParseAppVariableHashVariable.class.getName(), new ParseAppVariableHashVariable(), null));
        registrationList.add(context.registerService(ParseFormHashVariable.class.getName(), new ParseFormHashVariable(), null));
        
    }

    public void stop(BundleContext context) {
        for (ServiceRegistration registration : registrationList) {
            registration.unregister();
        }
    }
}