package in.gore;

import io.dropwizard.Configuration;
import io.dropwizard.jetty.HttpConnectorFactory;
import io.dropwizard.server.DefaultServerFactory;

/**
 * Created by mihirgore on 07/06/16.
 */
public class TestConfiguration extends Configuration {

    public TestConfiguration() {
        super();
        DefaultServerFactory servFactory = (DefaultServerFactory)getServerFactory();
        HttpConnectorFactory appConnector = (HttpConnectorFactory) servFactory.getApplicationConnectors().get(0);
        appConnector.setPort(9090);

        HttpConnectorFactory adminConnector = (HttpConnectorFactory)servFactory.getAdminConnectors().get(0);
        adminConnector.setPort(9091);
    }
}
