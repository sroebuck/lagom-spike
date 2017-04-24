package sample.traffic.impl;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;
import sample.echo.api.EchoService;
import sample.traffic.api.TrafficService;

public class TrafficServiceModule  extends AbstractModule implements ServiceGuiceSupport {

    @Override
    protected void configure() {
        bindServices(serviceBinding(TrafficService.class, TrafficServiceImpl.class));
        bindClient(EchoService.class);
    }

}
