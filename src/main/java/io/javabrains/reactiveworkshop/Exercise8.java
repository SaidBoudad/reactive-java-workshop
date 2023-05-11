package io.javabrains.reactiveworkshop;

import reactor.core.publisher.Flux;

import java.io.IOException;

public class Exercise8 {


    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumbersFluxWithException()

        // Print values from intNumbersFluxWithException and print a message when error happens
        ReactiveSources.intNumbersFluxWithException()
                .subscribe(num -> System.out.println(num),
                        ex -> System.out.println("An error happened"));
        // or by an alternative way
        ReactiveSources.intNumbersFluxWithException()
                .doOnError(er -> System.out.println("Error !!!" + er.getMessage())) //the error still occurs
                .subscribe(System.out::println);

        // Print values from intNumbersFluxWithException and continue on errors
        ReactiveSources.intNumbersFluxWithException()
                .onErrorContinue((e, item) -> System.out.println("Error !! " + e.getMessage())) //continue the Flux not ending it
                .subscribe(System.out::println);

        // Print values from intNumbersFluxWithException and when errors
        // happen, replace with a fallback sequence of -1 and -2
        ReactiveSources.intNumbersFluxWithException()
                .onErrorResume(e -> Flux.just(-1, -2)) //switch to the given new flux or mono
                .subscribe(System.out::println);

        System.out.println("Press a key to end");
        System.in.read();
    }

}
