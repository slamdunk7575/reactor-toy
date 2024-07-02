package com.yanggang.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

public class Operator2 {
    // concatMap : 동기적으로 처리해야 할때는 flatMap 대신 concatMap 사용
    public Flux<Integer> fluxConcatMap() {
        return Flux.range(1, 10)
                .concatMap(i -> Flux.range(i * 10, 10)
                        .delayElements(Duration.ofMillis(100)))
                .log();
    }

    // flatMapMany : Mono To Flux
    public Flux<Integer> monoFlatMapMany() {
        return Mono.just(10)
                .flatMapMany(i -> Flux.range(1, i))
                .log();
    }

    // defaultIfMany
    public Mono<Integer> defaultItEmpty() {
        return Mono.just(100)
                .filter(i -> i > 100)
                .defaultIfEmpty(10);
    }

    // switchIfMany
    public Mono<Integer> switchIfEmpty1() {
        return Mono.just(100)
                .filter(i -> i > 100)
                .switchIfEmpty(Mono.just(30).map(i -> i * 2));
    }

    public Mono<Integer> switchIfEmpty2() {
        return Mono.just(100)
                .filter(i -> i > 100)
                .switchIfEmpty(Mono.error(new RuntimeException("예외 발생")))
                .log();
    }

    // merge
    public Flux<String> fluxMerge() {
        return Flux.merge(Flux.fromIterable(List.of("a", "b", "c", "d", "e")), Flux.just("g"));
    }

    public Flux<String> monoMerge() {
        return Mono.just("1").mergeWith(Mono.just("2")).mergeWith(Mono.just("3"));
    }

    // zip: 각 인덱스에 맞춰 묶어서 연산을 할 수 있게 도와주는 연산자 (예: (a,b) (b,e) (c,f))
    public Flux<String> fluxZip() {
        return Flux.zip(Flux.just("a", "b", "c"), Flux.just("d", "e", "f"))
                .map(i -> i.getT1() + i.getT2())
                .log();
    }

    public Mono<Integer> monoZip() {
        return Mono.zip(Mono.just(1), Mono.just(2), Mono.just(3))
                .map(i -> i.getT1() + i.getT2() + i.getT3())
                .log();
    }
}
