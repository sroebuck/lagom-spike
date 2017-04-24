package sample.echo.impl;

import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;
import sample.echo.api.EchoService;

public class EchoServiceModule extends AbstractModule implements ServiceGuiceSupport {

  @Override
  protected void configure() {
    bindServices(serviceBinding(EchoService.class, EchoServiceImpl.class));
  }

}
