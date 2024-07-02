package com.yanggang.reactor;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class OperatorTest {

    private Operator operator = new Operator();

    @Test
    void fluxMap() {
        StepVerifier.create(operator.fluxMap())
                .expectNext(2, 4, 6, 8, 10)
                .verifyComplete();
    }

    @Test
    void fluxFilter() {
        StepVerifier.create(operator.fluxFilter())
                .expectNext(6, 7, 8, 9, 10)
                .verifyComplete();
    }

    @Test
    void fluxFilterTake() {
        StepVerifier.create(operator.fluxFilterTake())
                .expectNext(6, 7, 8)
                .verifyComplete();
    }

    @Test
    void fluxFlatMap() {
        StepVerifier.create(operator.fluxFlatMap())
                .expectNextCount(100)
                .verifyComplete();
    }

    @Test
    void fluxFlatMapGuGuDan() {
        StepVerifier.create(operator.fluxFlatMapGuGuDan())
                .expectNextCount(81)
                .verifyComplete();
    }
}
