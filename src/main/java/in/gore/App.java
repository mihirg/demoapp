package in.gore;

import in.gore.resources.QuoteResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;



public class App extends Application<TestConfiguration> {
	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

	@Override
	public void initialize(Bootstrap<TestConfiguration> b) {

	}

	@Override
	public void run(TestConfiguration c, Environment e) throws Exception {
		LOGGER.info("Method App#run called.");
		e.jersey().register(new QuoteResource());
		System.out.println("Helloworld");
	}

	public static void main(String args[]) {
		try {
			new App().run(args);
		} catch (Exception exp) {

		}
	}
}