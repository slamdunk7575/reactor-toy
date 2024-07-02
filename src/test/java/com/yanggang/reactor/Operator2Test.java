package com.yanggang.reactor;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class Operator2Test {

    private Operator2 operator2 = new Operator2();

    @Test
    void fluxConcatMap() {
        StepVerifier.create(operator2.fluxConcatMap())
                .expectNextCount(100)
                .verifyComplete();
    }

}
