package sample.echo.api;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.pathCall;

public interface EchoService extends Service {

    ServiceCall<NotUsed, String> echo(String message);
    ServiceCall<NotUsed, String> health();

    @Override
    default Descriptor descriptor() {
        return named("echo").withCalls(
            pathCall("/echo/:message", this::echo),
            pathCall("/health", this::health)
        ).withAutoAcl(true);
    }

}
