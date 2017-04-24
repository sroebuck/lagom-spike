package sample.traffic.impl;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lightbend.lagom.javadsl.immutable.ImmutableStyle;
import org.immutables.value.Value;

@Value.Immutable
@ImmutableStyle
@JsonDeserialize(as = EventMessageReceived.class)
public interface AbstractEventMessageReceived extends TrafficEvents {

    @Value.Parameter
    String message();

}
