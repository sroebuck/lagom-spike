package com.example;

import com.ecwid.consul.v1.ConsulClient;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.lightbend.lagom.javadsl.api.ServiceLocator;
import play.Configuration;
import play.api.Environment;
import play.api.inject.Binding;
import play.api.inject.Module;
import scala.collection.Seq;

public class ConsulServiceLocatorModule extends Module {
    private ConsulConfiguration consulConfiguration;

    @Inject
    public ConsulServiceLocatorModule(Environment environment, Configuration configuration) {
        this.consulConfiguration = new ConsulConfiguration(configuration);
    }

    @Override
    public Seq<Binding<?>> bindings(Environment environment, play.api.Configuration configuration) {
        return seq(
                bind(ServiceLocator.class).to(ConsulServiceLocator.class).in(Singleton.class),
                bind(ConsulClient.class).toInstance(new ConsulClient(consulConfiguration.getHostname(), consulConfiguration.getPort()))
        );
    }
}
