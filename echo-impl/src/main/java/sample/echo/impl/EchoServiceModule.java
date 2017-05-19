package sample.echo.impl;

import com.example.ConsulConfiguration;
import com.example.ConsulService;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.Configuration;
import play.Environment;
import sample.echo.api.EchoService;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class EchoServiceModule extends AbstractModule implements ServiceGuiceSupport {
    private final Logger log = LoggerFactory.getLogger(EchoServiceModule.class);

    private Configuration configuration;
    private ConsulConfiguration consulConfiguration;

    @SuppressWarnings("unused")
    private Environment environment;

    @Inject
    public EchoServiceModule(Environment environment, Configuration configuration) throws UnknownHostException {
        log.info("Instantiating echo service");
        this.environment = environment;
        this.configuration = configuration;
        this.consulConfiguration = new ConsulConfiguration(configuration);
        registerService();
    }

    @Override
    protected void configure() {
        bindServices(serviceBinding(EchoService.class, EchoServiceImpl.class));
    }

    private void registerService() throws UnknownHostException {
        log.info("Registering service");
        String hostname = InetAddress.getLocalHost().getHostAddress();
        int servicePort = Integer.parseInt(configuration.getString("http.port"));

        ConsulService echoService = new ConsulService("echo", hostname, servicePort, "health/");
        echoService.registerService(consulConfiguration.getHostname(), consulConfiguration.getPort());
    }
}
