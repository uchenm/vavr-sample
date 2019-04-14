package com.craft;

import io.vavr.control.Option;

import static io.vavr.API.*;
import static io.vavr.Patterns.*;
import static io.vavr.Predicates.*;

public class PatternMatchApp {
    public static void main(String[] args) {
        Integer i = 1;

        String s = Match(i).of(
                Case($(1), "one"),
                Case($(2), "two"),
                Case($(), "?")
        );
        System.out.println(s);

        String s1 = Match(i).of(
                Case($(is(1)), "one"),
                Case($(is(2)), "two"),
                Case($(), "?")
        );

        System.out.println(s1);

        String arg= "-h";

        Match(arg).of(
                Case($(isIn("-h", "--help")), o -> run(PatternMatchApp::displayHelp)),
                Case($(isIn("-v", "--version")), o -> run(PatternMatchApp::displayVersion)),
                Case($(), o -> run(() -> {
                    throw new IllegalArgumentException(arg);
                }))
        );

        Object obj= new Integer(10);

        Number plusOne = Match(obj).of(
                Case($(instanceOf(Integer.class)), it -> it + 1),
                Case($(instanceOf(Double.class)), d -> d + 1),
                Case($(), o -> { throw new NumberFormatException(); })
        );
        System.out.println(plusOne);


        Option<Integer> option= Option.of(10);

        String v= Match(option).of(
                Case($Some($()), value-> value+"th"),
                Case($None(), "empty")
        );
        System.out.println(v);
    }

    public static void displayHelp(){
        System.out.println("this is the help documents!");
    }
    public static void displayVersion(){
        System.out.println("this is the current version: v1.0 !");
    }
}
