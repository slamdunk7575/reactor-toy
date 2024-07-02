package com.yanggang.reactor;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class Operator1 {
    // map
    public Flux<Integer> fluxMap() {
        return Flux.range(1, 5)
                .map(i -> i * 2)
                .log();
    }

    // filter
    public Flux<Integer> fluxFilter() {
        return Flux.range(1, 10)
                .filter(i -> i > 5)
                .log();
    }

    // take
    public Flux<Integer> fluxFilterTake() {
        return Flux.range(1, 10)
                .filter(i -> i > 5)
                .take(3)
                .log();
    }

    // flatMap : 1:N 관계로 데이터 스트림을 다시 만듦 (비동적으로 처리)
    public Flux<Integer> fluxFlatMap() {
        return Flux.range(1, 10)
                .flatMap(i -> Flux.range(i * 10, 10)
                        .delayElements(Duration.ofMillis(100)))
                .log();
    }


    public Flux<Integer> fluxFlatMapGuGuDan() {
        return Flux.range(1, 9)
                .flatMap(i -> Flux.range(1, 9)
                        .map(j -> {
                            System.out.printf("%d * %d = %d\n", i, j, i * j);
                            return i * j;
                        }));
    }
}
