package sample.echo.impl;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import sample.echo.api.EchoService;

import javax.inject.Inject;

import static java.util.concurrent.CompletableFuture.completedFuture;

public class EchoServiceImpl implements EchoService {

    @Override
    public ServiceCall<NotUsed, String> echo(String message) {
        return request -> {
            // FIXME: Use logging!
            System.out.println("message: " + message);
            return completedFuture("message: " + message);
        };
    }

}
