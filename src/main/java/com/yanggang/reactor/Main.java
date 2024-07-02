package com.yanggang.reactor;

public class Main {
    public static void main(String[] args) {

        /*
           Publisher     <-(subscribe)     Subscriber                         : Subscriber 가 Publisher 를 subscribe 하게되면
                         ->(onSubscribe)                                      : Publisher 는 onSubscribe 호출
                         <-(request(n) / cancel)                              : Subscriber 는 데이터를 request
                         ->(onNext(), onError(), onComplete())                : Publisher 는 데이터를 전달하고 오류가 있으면 onError, 정상적으로 데이터를 전달했다면 onComplete 호출


           [ INFO] (main) | onSubscribe([Synchronous Fuseable] FluxRange.RangeSubscription)
           [ INFO] (main) | request(unbounded)
           [ INFO] (main) | onNext(1)
           1
           [ INFO] (main) | onNext(2)
           2
           ...
           10
           [ INFO] (main) | onComplete()
        */

        var publisher = new Publisher();
        publisher.startFluxRange().subscribe(System.out::println);

        /*
           [ INFO] (main) | onSubscribe([Synchronous Fuseable] Operators.ScalarSubscription)
           [ INFO] (main) | request(unbounded)
           [ INFO] (main) | onNext(1)
           1
           [ INFO] (main) | onComplete()
        */
        publisher.startMono().subscribe(System.out::println);

        /*
           [ INFO] (main) onSubscribe([Fuseable] Operators.EmptySubscription)
           [ INFO] (main) request(unbounded)                                : Subscriber 가 request 했지만 바로 종료 (데이터가 없어서 onNext 를 보내지 않음)
           [ INFO] (main) onComplete()
        */
        publisher.startMonoEmpty().subscribe();
    }
}
