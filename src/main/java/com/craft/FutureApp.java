package com.craft;

import io.vavr.concurrent.Future;

public class FutureApp {
    public static void main(String[] args) throws Exception{
        Future<String> future = Future.of(() -> {
            Thread.sleep(1000);
            return "Completed";
        });

        Thread.sleep(2000);
        System.out.println(future.get());
        System.out.println(future.isCompleted());
    }
}
