package com.yanggang.reactor;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class SchedulerTest {

    private Scheduler scheduler = new Scheduler();

    @Test
    void fluxMapWithSubscribeOn() {
        StepVerifier.create(scheduler.fluxMapWithSubscribeOn())
                .expectNextCount(10)
                .verifyComplete();
    }

    @Test
    void fluxMapWithPublishOn() {
        StepVerifier.create(scheduler.fluxMapWithPublishOn())
                .expectNextCount(10)
                .verifyComplete();
    }
}
