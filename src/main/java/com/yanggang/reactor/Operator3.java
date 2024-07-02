package com.yanggang.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class Operator3 {
    // count
    public Mono<Long> fluxCount() {
        return Flux.range(1, 10).count();
    }

    // distinct
    public Flux<String> fluxDistinct() {
        return Flux.fromIterable(List.of("a", "b", "a", "b", "c", "d"))
                .distinct()
                .log();
    }

    // reduce
    public Mono<Integer> fluxReduce() {
        return Flux.range(1, 10)
                .reduce((i, j) -> i + j)
                .log();
    }

    // groupBy
    public Flux<Integer> fluxGroupBy() {
        return Flux.range(1, 10)
                .groupBy(i -> (i % 2 == 0) ? "even" : "odd")
                .flatMap(group -> group.reduce((i, j) -> i + j))
                .log();
    }
}
