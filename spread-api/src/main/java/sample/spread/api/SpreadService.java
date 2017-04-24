package sample.spread.api;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.pathCall;

public interface SpreadService extends Service {

    ServiceCall<NotUsed, String> spread(String message);

    @Override
    default Descriptor descriptor() {
        return named("spreadservice").withCalls(
                pathCall("/api/spread/:message", this::spread)
        ).withAutoAcl(true);
    }
}
