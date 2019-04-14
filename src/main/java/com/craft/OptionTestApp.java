package com.craft;

import io.vavr.control.Option;

import java.util.Optional;

public class OptionTestApp {

    public static void main(String[] args) {

        java8OptionalTest();
        wrongUseVavrOptionTest();
        correctUseVavrOption();
    }

    public static void java8OptionalTest(){
        Optional<String> maybeFoo = Optional.of("foo");
        //then(maybeFoo.get()).isEqualTo("foo");
        System.out.println(maybeFoo.get());

        Optional<String> maybeFooBar = maybeFoo.map(s -> (String)null)
                .map(s -> s.toUpperCase() + "bar");
//        then(maybeFooBar.isPresent()).isFalse();
        System.out.println(maybeFooBar.isPresent());
    }


    public static void wrongUseVavrOptionTest(){
        Option<String> maybeFoo = Option.of("foo");
//        then(maybeFoo.get()).isEqualTo("foo");
        try {
            maybeFoo.map(s -> (String)null)
                    .map(s -> s.toUpperCase() + "bar");
//            Assert.fail();
        } catch (NullPointerException e) {
            // this is clearly not the correct approach
            e.printStackTrace();
        }
    }

    public static void correctUseVavrOption(){
        Option<String> maybeFoo = Option.of("foo");
//        then(maybeFoo.get()).isEqualTo("foo");
        System.out.println(maybeFoo.get());
        Option<String> maybeFooBar = maybeFoo.map(s -> (String)null)
                .flatMap(s -> Option.of(s)
                        .map(t -> t.toUpperCase() + "bar"));
        System.out.println(maybeFooBar.isEmpty());
//        then(maybeFooBar.isEmpty()).isTrue();

        Option<String> maybeFooBar2 = maybeFoo.flatMap(s -> Option.of((String)null))
                .map(s -> s.toUpperCase() + "bar");
        System.out.println(maybeFooBar2.isEmpty());
    }


}
