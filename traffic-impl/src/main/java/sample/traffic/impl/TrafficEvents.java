package sample.traffic.impl;

import com.lightbend.lagom.javadsl.persistence.AggregateEvent;
import com.lightbend.lagom.javadsl.persistence.AggregateEventShards;
import com.lightbend.lagom.javadsl.persistence.AggregateEventTag;
import com.lightbend.lagom.javadsl.persistence.AggregateEventTagger;
import com.lightbend.lagom.serialization.Jsonable;


public interface TrafficEvents extends Jsonable, AggregateEvent<TrafficEvents> {

    int NUM_SHARDS = 3;

    AggregateEventShards<TrafficEvents> TAG = AggregateEventTag.sharded(TrafficEvents.class, NUM_SHARDS);

    @Override
    default AggregateEventTagger<TrafficEvents> aggregateTag() {
        return TAG;
    }

}
