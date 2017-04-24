package sample.traffic.impl;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRegistry;
import sample.echo.api.EchoService;
import sample.traffic.api.TrafficService;

import javax.inject.Inject;

import static java.util.concurrent.CompletableFuture.completedFuture;

public class TrafficServiceImpl implements TrafficService {

    private final EchoService echoService;
    private final PersistentEntityRegistry persistentEntityRegistry;

    @Inject
    public TrafficServiceImpl(
            PersistentEntityRegistry persistentEntityRegistry,
            EchoService echoService) {
        this.echoService = echoService;
        this.persistentEntityRegistry = persistentEntityRegistry;
    }

    @Override
    public ServiceCall<NotUsed, String> traffic(String message) {
        return request -> {
            echoService.echo(message).invoke();
            return completedFuture("traffic: " + message);
        };
    }
}
