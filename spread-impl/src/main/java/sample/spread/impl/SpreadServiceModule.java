package sample.spread.impl;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;
import sample.spread.api.SpreadService;
import sample.traffic.api.TrafficService;

public class SpreadServiceModule  extends AbstractModule implements ServiceGuiceSupport {

    @Override
    protected void configure() {
        bindServices(serviceBinding(SpreadService.class, SpreadServiceImpl.class));
        bindClient(TrafficService.class);
    }

}
