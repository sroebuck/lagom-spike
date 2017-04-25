package sample.traffic.impl;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lightbend.lagom.javadsl.immutable.ImmutableStyle;
import com.lightbend.lagom.serialization.Jsonable;
import org.immutables.value.Value;

import java.util.Optional;

@Value.Immutable
@ImmutableStyle
@JsonDeserialize(as = TrafficState.class)
public interface AbstractTrafficState extends Jsonable {

    @Value.Parameter
    Optional<String> currentMessageOpt();

    @Value.Parameter
    Optional<String> lastMessageOpt();

}