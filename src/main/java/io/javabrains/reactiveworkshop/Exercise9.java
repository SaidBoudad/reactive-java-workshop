package io.javabrains.reactiveworkshop;

import reactor.core.publisher.SignalType;

import java.io.IOException;

public class Exercise9 {


    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumbersFlux()

        // Print size of intNumbersFlux after the last item returns
        ReactiveSources.intNumbersFluxWithException()
                .doFinally(signalType -> {
                    if (signalType == SignalType.ON_COMPLETE) {
                        System.out.println("Done!");
                    } else if (signalType == SignalType.ON_ERROR) {
                        {
                            System.out.println("Error!");
                        }
                    }

                })
                .subscribe(s);

        // Collect all items of intNumbersFlux into a single list and print it
        // TODO: Write code here

        // Transform to a sequence of sums of adjacent two numbers
        // TODO: Write code here

        System.out.println("Press a key to end");
        System.in.read();
    }

}
