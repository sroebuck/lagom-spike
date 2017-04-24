package sample.spread.impl;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import sample.spread.api.SpreadService;
import sample.traffic.api.TrafficService;

import javax.inject.Inject;

import static java.util.concurrent.CompletableFuture.completedFuture;

public class SpreadServiceImpl implements SpreadService {

    private final TrafficService trafficService;

    @Inject
    public SpreadServiceImpl(TrafficService trafficService) {
        this.trafficService = trafficService;
    }

    @Override
    public ServiceCall<NotUsed, String> spread(String message) {
        return request -> {
            final char[] chars = {'A','B','C','D','E','F','G','H'};
            for (char c: chars) {
                String m = message + "-" + c;
                trafficService.traffic(m).invoke();
            }
            return completedFuture("spread: " + message);
        };
    }
}
