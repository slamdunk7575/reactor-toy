package com.yanggang.reactor;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class Operator2 {
    // concatMap : 동기적으로 처리해야 할때는 flatMap 대신 concatMap 사용
    public Flux<Integer> fluxConcatMap() {
        return Flux.range(1, 10)
                .concatMap(i -> Flux.range(i * 10, 10)
                        .delayElements(Duration.ofMillis(100)))
                .log();
    }

    // flatMapMany

    // switchIfMany

    // defaultIfMany

    // merge & zip
}
