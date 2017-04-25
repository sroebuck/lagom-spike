package sample.traffic.impl;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.persistence.PersistentEntityRef;
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

        persistentEntityRegistry.register(TrafficEntity.class);
    }

    @Override
    public ServiceCall<NotUsed, String> traffic(String message) {
        return request -> {
            PersistentEntityRef<TrafficCommands> ref =
                    persistentEntityRegistry.refFor(TrafficEntity.class, message.substring(message.length() - 1));
            final CommandTraffic cmd = CommandTraffic.of(message);
            return ref.ask(cmd).thenApply(ack -> "traffic: " + message);
        };
    }
}
