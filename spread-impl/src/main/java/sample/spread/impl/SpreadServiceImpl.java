package sample.spread.impl;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import sample.spread.api.SpreadService;

import static java.util.concurrent.CompletableFuture.completedFuture;

public class SpreadServiceImpl implements SpreadService {

    @Override
    public ServiceCall<NotUsed, String> spread(String message) {
        return request -> completedFuture("spread: " + message);
    }
}
