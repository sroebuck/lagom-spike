package sample.traffic.api;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.pathCall;

public interface TrafficService extends Service {

    ServiceCall<NotUsed, String> traffic(String message);

    @Override
    default Descriptor descriptor() {
        return named("trafficservice").withCalls(
                pathCall("/api/traffic/:message", this::traffic)
        ).withAutoAcl(true);
    }
}
