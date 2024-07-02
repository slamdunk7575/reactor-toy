package com.yanggang.reactor;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class Operator4 {

    // Back-Pressure 관련 연산자들
    // delaySequence & limit
    /*  Subscriber 가 request 를 2개 하고 Publisher 는 그에 맞게 2개씩 전달
        [ INFO] (Test worker) onSubscribe(SerializedSubscriber)
        [ INFO] (Test worker) request(2)
        [ INFO] (parallel-1) onNext(1)
        [ INFO] (parallel-1) onNext(2)
        [ INFO] (parallel-1) request(2)
        ...
     */
    public Flux<Integer> fluxDelayAndLimit() {
        return Flux.range(1, 10)
                .delaySequence(Duration.ofMillis(1))
                .log()
                .limitRate(2);
    }

    // sample : Duration 동안 받은 데이터중 일부만 전달(샘플링)하는 연산자
    /*
        [ INFO] (Test worker) onSubscribe(FluxSample.SampleMainSubscriber)
        [ INFO] (Test worker) request(unbounded)
        [ INFO] (parallel-1) onNext(2)
        [ INFO] (parallel-1) onNext(5)
        [ INFO] (parallel-1) onNext(8)
        [ INFO] (parallel-1) onNext(11)
        ...
     */
    public Flux<Integer> fluxSample() {
        return Flux.range(1, 100)
                .delayElements(Duration.ofMillis(100)) // Delay 를 줘서 sampling 하는 시간을 확보
                .sample(Duration.ofMillis(300))
                .log();
    }
}
