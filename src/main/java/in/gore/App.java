package in.gore;

import in.gore.jmx.impl.CacheCounterMBeanImpl;
import in.gore.jmx.impl.SingletonMBeanImpl;
import in.gore.resources.DetailsResource;
import in.gore.resources.QuoteResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.lang.management.ManagementFactory;
import javax.management.MBeanServer;
import javax.management.ObjectName;


public class App extends Application<TestConfiguration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    @Override
    public void initialize(Bootstrap<TestConfiguration> b) {

    }

    @Override
    public void run(TestConfiguration c, Environment e) throws Exception {
        LOGGER.info("Method App#run called.");
        e.jersey().register(new QuoteResource());
        e.jersey().register(new DetailsResource());

        // register MBean with jmx server.
        // refer to http://www.oracle.com/us/technologies/java/best-practices-jsp-136021.html
        // for details on naming conventions.
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        mbs.registerMBean(new CacheCounterMBeanImpl(), new ObjectName("in.gore:type=CacheCounter,name=Test1"));
        mbs.registerMBean(new CacheCounterMBeanImpl(), new ObjectName("in.gore:type=CacheCounter,name=Test2"));
        mbs.registerMBean(new SingletonMBeanImpl(), new ObjectName("in.gore:type=ClassesLoaded"));
        LOGGER.info("MBeans registered");

    }

    public static void main(String args[]) {
        try {
            new App().run(args);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }
}