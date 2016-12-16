package in.gore;

import in.gore.jmx.api.TestMXBean;
import in.gore.jmx.impl.TestMBeanImpl;
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

		// register MBean with jmx server.
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		ObjectName objName = new ObjectName("in.gore:type=TestBean");
		TestMXBean mb = new TestMBeanImpl();
		mbs.registerMBean(mb, objName);

        LOGGER.info("MBean registered");
	}

	public static void main(String args[]) {
		try {
			new App().run(args);
		} catch (Exception exp) {
            System.out.println(exp);
		}
	}
}