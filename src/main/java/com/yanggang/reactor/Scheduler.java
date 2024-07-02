package com.yanggang.reactor;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Scheduler {

    // Schedulers : 비동기 작업을 실행하고 관리하고 조절하는데 사용
    // 다양한 타입의 Schedulers 를 제공 (쓰레드풀 관리, 동시성 제어)

    // subscribeOn 은 스트림을 Subscribe 하는 동안 해당 스케줄러를 사용하겠다 라는 의미
    /*
        [ INFO] (Test worker) onSubscribe(FluxSubscribeOn.SubscribeOnSubscriber)
        [ INFO] (Test worker) request(unbounded)
        [ INFO] (boundedElastic-1) onNext(2) -> 별도의 쓰레드에서 동작
        [ INFO] (boundedElastic-1) onNext(4)
        ...
     */
    public Flux<Integer> fluxMapWithSubscribeOn() {
        return Flux.range(1, 10)
                .map(i -> i * 2)
                .subscribeOn(Schedulers.boundedElastic())
                .log();
    }

    // publishOn 은 다음 구문부터 해당 Scheduler 를 쓰겠다는 의미
    /*
       [ INFO] (Test worker) | onSubscribe([Fuseable] FluxPublishOn.PublishOnSubscriber)
       [ INFO] (Test worker) | onSubscribe([Fuseable] FluxPublishOn.PublishOnSubscriber)
       [ INFO] (Test worker) | request(unbounded)
       [ INFO] (Test worker) | request(256)
       [ INFO] (boundedElastic-1) | onNext(2)
       [ INFO] (boundedElastic-1) | onNext(3)
       [ INFO] (parallel-1) | onNext(2)
       [ INFO] (boundedElastic-1) | onNext(4)
       [ INFO] (parallel-1) | onNext(3)
       [ INFO] (parallel-1) | onNext(4)
       ...
     */
    public Flux<Integer> fluxMapWithPublishOn() {
        return Flux.range(1, 10)
                .map(i -> i + 1)
                .publishOn(Schedulers.boundedElastic())
                .log()
                .publishOn(Schedulers.parallel())
                .log()
                .map(i -> i * 2);
    }
}
