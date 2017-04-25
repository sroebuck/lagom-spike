package sample.echo.impl;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sample.echo.api.EchoService;

import javax.inject.Inject;

import static java.util.concurrent.CompletableFuture.completedFuture;

public class EchoServiceImpl implements EchoService {

    private final Logger log =
            LoggerFactory.getLogger(EchoServiceImpl.class);

    @Override
    public ServiceCall<NotUsed, String> echo(String message) {
        return request -> {
            final String entityHashCode = Integer.toHexString(System.identityHashCode(this));
            log.info("echo [{}]: {}", entityHashCode, message);
            return completedFuture("echo: " + message);
        };
    }

}
