package io.javabrains.reactiveworkshop;

public class Exercise1 {

    public static void main(String[] args) {

        // Use StreamSources.intNumbersStream() and StreamSources.userStream()

        // 1 Print all numbers in the intNumbersStream stream
        StreamSources.intNumbersStream().forEach(n -> System.out.println(n));

        // 2 Print numbers from intNumbersStream that are less than 5
        StreamSources.intNumbersStream().filter(n -> n < 5).forEach(System.out::println);

        // 3 Print the second and third numbers in intNumbersStream that's greater than 5
        StreamSources.intNumbersStream().filter(n -> n > 5)
                .skip(1)
                .limit(2)
                .forEach(System.out::println);

        // 4 Print the first number in intNumbersStream that's greater than 5.
        //  If nothing is found, print -1
        Integer value = StreamSources.intNumbersStream().filter(n -> n > 5)
                .findFirst().orElse(-1);
        System.out.println(value);

        // 5 Print first names of all users in userStream
        StreamSources.userStream().forEach(n -> System.out.println(n.getFirstName()));
        StreamSources.userStream()
                .map(user -> user.getFirstName())
                .forEach(System.out::println);

        // 6 Print first names in userStream for users that have IDs from number stream
        StreamSources.intNumbersStream()
                .map(id -> StreamSources.userStream().filter(user -> user.getId() == id))
                .forEach(System.out::println);
        StreamSources.userStream()
                .filter(u -> StreamSources.intNumbersStream().anyMatch(i -> i == u.getId()))
                .forEach(System.out::println);


        //        Testing the time in normal and in parallel stream

        System.out.println("Testing the time in normal and in parallel stream ");
        long start1 = System.currentTimeMillis();
        StreamSources.intNumbersStream().flatMap(id -> StreamSources.userStream().filter(user -> user.getId() == id))
                .map(user -> user.getFirstName())
                .forEach(x -> System.out.println("thread : " + Thread.currentThread().getName() + " " + x));
//                .forEach(username -> System.out.println(username));
        long end1 = System.currentTimeMillis();
        System.out.println("the time without parallel stream is : " + (end1 - start1));

        long start = System.currentTimeMillis();
        StreamSources.intNumbersStream().parallel().flatMap(id -> StreamSources.userStream().filter(user -> user.getId() == id))
                .map(user -> user.getFirstName())
                .forEach(x -> System.out.println("thread : " + Thread.currentThread().getName() + " " + x));
//                .forEach(username -> System.out.println(username));
        var end = System.currentTimeMillis();
        System.out.println("the time in parallel stream is : " + (end - start));


    }

}
