package sample.traffic.impl;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import sample.traffic.api.TrafficService;

import static java.util.concurrent.CompletableFuture.completedFuture;

public class TrafficServiceImpl implements TrafficService {

    @Override
    public ServiceCall<NotUsed, String> traffic(String message) {
        return request -> completedFuture("traffic: " + message);
    }
}
