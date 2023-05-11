package io.javabrains.reactiveworkshop;

import reactor.core.publisher.Flux;
import reactor.core.publisher.SignalType;

import java.io.IOException;

public class Exercise9 {


    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumbersFlux()
        Flux.just(1, 2, 3)
                .map(i -> i * 2)
                .map(i -> {
                    if (i == 4) {
                        throw new RuntimeException();
                    }
                    return i;
                })
                .doFinally(signalType -> {
                    if (signalType == SignalType.ON_COMPLETE) {
                        System.out.println("Flux completed successfully");
                    } else if (signalType == SignalType.ON_ERROR) {
                        System.out.println("Flux completed with an error");
                    }
                })
                .subscribe(System.out::println);

        // Print size of intNumbersFlux after the last item returns
        // this methods convert Flux to Mono  count()
        ReactiveSources.intNumbersFlux()
                .count()
                .subscribe(System.out::println);


        // Collect all items of intNumbersFlux into a single list and print
        // this methods convert Flux to Mono  collectList()
        ReactiveSources.intNumbersFlux()
                .collectList()
                .subscribe(System.out::println);

        // Transform to a sequence of sums of adjacent two numbers
        ReactiveSources.intNumbersFlux()
                .buffer(2)
                .map(list -> list.get(0) + list.get(1))
                .subscribe(System.out::println);

        System.out.println("Press a key to end");
        System.in.read();
    }

}
