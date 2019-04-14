package com.craft;

import io.vavr.control.Try;



public class TryUseApp {

    public static void main(String[] args) {

        String[] array = {};
        String result = Try.ofCallable(() -> array[1]).getOrElse("default");

//        assertTrue(result == "default");
        System.out.println(result == "default");

//        R result = Try(() -> divide(10,0))
//                .recover(x -> ...)
//                .getOrElse(defaultValue);

        System.out.println(TryUseApp.divide(10,0));
        System.out.println(TryUseApp.divide(10,2));

    }

    public static Try<Integer> divide(Integer dividend, Integer divisor) {
        return Try.of(() -> dividend / divisor);
    }

}
