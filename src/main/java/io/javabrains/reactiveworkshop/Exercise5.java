package io.javabrains.reactiveworkshop;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

import java.io.IOException;

public class Exercise5 {

    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumberMono() and ReactiveSources.userMono()

        // Subscribe to a flux using the error and completion hooks
        ReactiveSources.stringNumbersFlux().subscribe(number -> System.out.println(number),
                error -> System.out.println(error.getMessage()),
                () -> System.out.println("complete"));

        // Subscribe to a flux using an implementation of BaseSubscriber
        // this way is used if you need a control of the flow by changing the quantity of item in request() method
        // for handling the data that is emitted by the publisher.
        // It is important to properly manage the demand for items to avoid backpressure and ensure efficient processing of data.
        ReactiveSources.intNumbersFlux().subscribe(new MySubscriber<>());


        System.out.println("Press a key to end");
        System.in.read();
    }

}

class MySubscriber<T> extends BaseSubscriber<T> {
    public void hookOnSubscribe(Subscription subscription) {
        System.out.println("subscribed happend");
        request(2);
    }

    public void hookOnNext(T value) {
        System.out.println(value.toString() + "received");
        request(2); //this is give a message 'OK to push' but not pull
    }
}