package sample.spread.impl;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sample.spread.api.SpreadService;
import sample.traffic.api.TrafficService;

import javax.inject.Inject;

import static java.util.concurrent.CompletableFuture.completedFuture;

public class SpreadServiceImpl implements SpreadService {

    private final Logger log =
            LoggerFactory.getLogger(SpreadServiceImpl.class);

    private final TrafficService trafficService;

    @Inject
    public SpreadServiceImpl(TrafficService trafficService) {
        this.trafficService = trafficService;
    }

    @Override
    public ServiceCall<NotUsed, String> spread(String message) {
        return request -> {
            final String entityHashCode = Integer.toHexString(System.identityHashCode(this));
            log.warn("spread [{}]: {}", entityHashCode, message);

            final char[] chars = {'A','B','C','D','E','F','G','H'};
            for (char c: chars) {
                String m = message + "-" + c;
                trafficService.traffic(m).invoke();
            }
            return completedFuture("spread: " + message);
        };
    }
}
