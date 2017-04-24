package sample.traffic.impl;

import akka.Done;
import com.lightbend.lagom.javadsl.persistence.PersistentEntity;
import jdk.nashorn.internal.runtime.regexp.joni.Option;

import java.util.Optional;

public class TrafficEntity extends PersistentEntity<TrafficCommands, TrafficEvents, TrafficState> {

    @Override
    public Behavior initialBehavior(Optional<TrafficState> snapshotState) {
        BehaviorBuilder b = newBehaviorBuilder(TrafficState.of(Optional.empty()));
        b.setCommandHandler(CommandTraffic.class,
            (cmd, ctx) -> ctx.thenPersist(
                    EventMessageReceived.of(cmd.message()),
                    evt -> ctx.reply(Done.getInstance()))
            );
        b.setEventHandler(EventMessageReceived.class, evt -> TrafficState.of(Optional.of(evt.message())));
        return b.build();
    }

}
