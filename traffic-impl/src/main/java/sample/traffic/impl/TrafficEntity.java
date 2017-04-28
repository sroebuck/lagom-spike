package sample.traffic.impl;

import akka.Done;
import com.lightbend.lagom.javadsl.persistence.PersistentEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sample.echo.api.EchoService;

import javax.inject.Inject;
import java.util.Optional;

public class TrafficEntity extends PersistentEntity<TrafficCommands, TrafficEvents, TrafficState> {

    private final EchoService echoService;

    private final Logger log =
            LoggerFactory.getLogger(TrafficEntity.class);

    @Inject
    public TrafficEntity(EchoService echoService) {
        this.echoService = echoService;
    }

    @Override
    public Behavior initialBehavior(Optional<TrafficState> snapshotState) {
        BehaviorBuilder b = newBehaviorBuilder(
                snapshotState.orElse( TrafficState.of(Optional.empty(), Optional.empty()) )
        );

        b.setCommandHandler(CommandTraffic.class,
            (cmd, ctx) -> ctx.thenPersist(EventMessageReceived.of(cmd.message()),
                    evt -> {
                        final String combined = state().lastMessageOpt().isPresent() ?
                                state().lastMessageOpt().get() + " -> " + cmd.message() : cmd.message();
                        final String entityHashCode = Integer.toHexString(System.identityHashCode(this));
                        log.warn("traffic-{} [{}]: {}", this.entityId(), entityHashCode, combined);
                        final String combinedWithTrafficClassHash = combined + "@" + entityHashCode;
                        echoService.echo(combinedWithTrafficClassHash).invoke();
                        ctx.reply(Done.getInstance());
                    })
            );
        b.setEventHandler(
                EventMessageReceived.class,
                evt -> state().withLastMessageOpt(state().currentMessageOpt()).withCurrentMessageOpt(evt.message())
            );
        return b.build();
    }

}
