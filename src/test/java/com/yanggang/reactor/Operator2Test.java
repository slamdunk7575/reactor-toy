package com.yanggang.reactor;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class Operator2Test {

    private Operator2 operator2 = new Operator2();

    @Test
    void fluxConcatMap() {
        StepVerifier.create(operator2.fluxConcatMap())
                .expectNextCount(100)
                .verifyComplete();
    }

    @Test
    void monoFlatMapMany() {
        StepVerifier.create(operator2.monoFlatMapMany())
                .expectNext(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .verifyComplete();
    }

    @Test
    void defaultItEmpty() {
        StepVerifier.create(operator2.defaultItEmpty())
                .expectNext(10)
                .verifyComplete();
    }

    @Test
    void switchIfEmpty1() {
        StepVerifier.create(operator2.switchIfEmpty1())
                .expectNext(60)
                .verifyComplete();
    }

    @Test
    void switchIfEmpty2() {
        StepVerifier.create(operator2.switchIfEmpty2())
                .expectError(RuntimeException.class)
                .verify();
    }

    @Test
    void fluxMerge() {
        StepVerifier.create(operator2.fluxMerge())
                .expectNext("a", "b", "c", "d", "e", "g")
                .verifyComplete();
    }

    @Test
    void monoMerge() {
        StepVerifier.create(operator2.monoMerge())
                .expectNext("1", "2", "3")
                .verifyComplete();
    }

    @Test
    void fluxZip() {
        StepVerifier.create(operator2.fluxZip())
                .expectNext("ad", "be", "cf")
                .verifyComplete();
    }

    @Test
    void monoZip() {
        StepVerifier.create(operator2.monoZip())
                .expectNext(6)
                .verifyComplete();
    }
}
