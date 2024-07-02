package com.yanggang.reactor;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class PublisherTest {

    private Publisher publisher = new Publisher();

    @Test
    void startFlux() {
        StepVerifier.create(publisher.startFluxRange())
                .expectNext(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .verifyComplete();
    }

    @Test
    void startFluxFromIterable() {
        StepVerifier.create(publisher.startFluxFromIterable())
                .expectNext("a")
                .expectNext("b")
                .expectNext("c")
                .expectNext("d")
                .expectNext("e")
                .expectNext("f")
                .expectNext("g")
                .verifyComplete();
    }

    @Test
    void startMono() {
        StepVerifier.create(publisher.startMono())
                .expectNext(1)
                .verifyComplete();
    }

    @Test
    void startMonoEmpty() {
        StepVerifier.create(publisher.startMonoEmpty())
                .verifyComplete();
    }

    @Test
    void startMonoError() {
        StepVerifier.create(publisher.startMonoError())
                .expectError(RuntimeException.class)
                .verify();
    }
}
