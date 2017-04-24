package sample.traffic.impl;

import akka.Done;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lightbend.lagom.javadsl.immutable.ImmutableStyle;
import com.lightbend.lagom.javadsl.persistence.PersistentEntity.ReplyType;
import org.immutables.value.Value;

@Value.Immutable
@ImmutableStyle
@JsonDeserialize(as = CommandTraffic.class)
interface AbstractCommandTraffic extends TrafficCommands, ReplyType<Done> {

    @Value.Parameter
    String message();

}
