package sample.traffic.impl;

import akka.actor.ActorSystem;
import akka.testkit.JavaTestKit;
import com.lightbend.lagom.javadsl.testkit.PersistentEntityTestDriver;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class TrafficEntityTest {

    static ActorSystem system;

    @BeforeClass
    public static void setup() {
        system = ActorSystem.create();
    }

    @AfterClass
    public static void teardown() {
        JavaTestKit.shutdownActorSystem(system);
        system = null;
    }

    @Test
    public void testTrafficMessages() {
        PersistentEntityTestDriver<TrafficCommands, TrafficEvents, TrafficState> driver =
                new PersistentEntityTestDriver<>(system, new TrafficEntity(), "trafficEntity-1");

        String message = "12345-A";
        PersistentEntityTestDriver.Outcome<TrafficEvents, TrafficState> outcome = driver.run(
                CommandTraffic.of(message));
        assertEquals(EventMessageReceived.of(message),
                outcome.events().get(0));
        assertEquals(1, outcome.events().size());
        assertEquals(Optional.of(message), outcome.state().lastMessageOpt());
        assertEquals(Collections.emptyList(), outcome.issues());

        String message2 = "234-A";
        PersistentEntityTestDriver.Outcome<TrafficEvents, TrafficState> outcome2 = driver.run(
                CommandTraffic.of(message2));
        assertEquals(EventMessageReceived.of(message2),
                outcome2.events().get(0));
        assertEquals(1, outcome2.events().size());
        assertEquals(Optional.of(message2), outcome2.state().lastMessageOpt());
        assertEquals(Collections.emptyList(), outcome2.issues());
    }

}