package it.djacomo.osgi;

import com.dotmarketing.loggers.Log4jUtil;
import com.dotmarketing.osgi.GenericBundleActivator;
import it.djacomo.osgi.actionlet.HelloWorldActionlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.osgi.framework.BundleContext;

public class Activator extends GenericBundleActivator {

    private LoggerContext pluginLoggerContext;
    
    private final Logger log = LogManager.getLogger(this.getClass());

    @Override
    public void start ( BundleContext bundleContext ) throws Exception {

        //Initializing log4j...
        LoggerContext dotcmsLoggerContext = Log4jUtil.getLoggerContext();
        
        //Initialing the log4j context of this plugin based on the dotCMS logger context
        pluginLoggerContext = (LoggerContext) LogManager
                .getContext(this.getClass().getClassLoader(),
                        false,
                        dotcmsLoggerContext,
                        dotcmsLoggerContext.getConfigLocation());

        //Initializing services...
        initializeServices( bundleContext );

        //Registering the test Actionlet
        registerActionlet( bundleContext, new HelloWorldActionlet() );
    
        log.info("Base Archetype Started");
    }

    public void stop(BundleContext context) throws Exception {

        //Unregister all the bundle services
        unregisterServices(context);

        log.info("Base Archetype Stopped");
        
        //Shutting down log4j in order to avoid memory leaks
        Log4jUtil.shutdown(pluginLoggerContext);
    }

}