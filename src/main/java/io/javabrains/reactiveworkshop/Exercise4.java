package io.javabrains.reactiveworkshop;

import java.io.IOException;
import java.util.Optional;

public class Exercise4 {

    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumberMono()

        // Print the value from intNumberMono when it emits
        ReactiveSources.intNumberMono().subscribe(
                number -> System.out.println(number),
                error -> System.out.println(error.getMessage()),
                () -> System.out.println("Complete"));

        // Get the value from the Mono into an integer variable
        Integer number = ReactiveSources.intNumberMono().block();//this method will block until this event emit
        System.out.println(number);

        //  using block() should be avoided as it can potentially block the thread and cause performance issues
        //  or with Optional<T>
        Optional<Integer> number1 = ReactiveSources.intNumberMono().blockOptional();
        System.out.println(number1);

        System.out.println("Press a key to end");
        System.in.read();


        // how to use mono and flux to increase eficiency
        // For example, instead of using block() to wait for a result, you can return a Mono or Flux
        // from your controller method, which will emit the result when it is available.
        // The calling thread can then subscribe to the Mono or Flux and continue processing other requests
        // while waiting for the result.
        //@GetMapping("/hello")
        //public Mono<String> sayHello() {
        //    return Mono.just("Hello, World!");
        //This method returns a Mono<String> that emits the string "Hello, World!" when subscribed to.
        // The calling thread can then continue processing other requests while waiting for the result.
    }

}
