package com.yanggang.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class Publisher {
    public Flux<Integer> startFluxRange() {
        return Flux.range(1 , 10).log();
    }

    public Flux<String> startFluxFromIterable() {
        return Flux.fromIterable(List.of("a", "b", "c", "d", "e", "f", "g"))
                .log();
    }

    public Mono<Integer> startMono() {
        return Mono.just(1).log();
    }

    public Mono<?> startMonoEmpty() {
        return Mono.empty().log();
    }

    public Mono<?> startMonoError() {
        return Mono.error(new RuntimeException("예외 발생"))
                .log();
    }
}
