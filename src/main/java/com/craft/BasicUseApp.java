package com.craft;

import io.vavr.*;
import io.vavr.API.*;
import io.vavr.control.Option;

public class BasicUseApp {

    public static void main(String[] args) {
        tupleTest();
        functionTest();
        compositionTest();
        LiftingTest();
        PartialApplicationTest();
        curryTest();
        memorizationTest();
    }

    public static void tupleTest(){
        Tuple2<String, Integer> java8 = Tuple.of("Java", 8);
//        String s = java8._1;
//        Integer n= java8._2;

//        System.out.println(s);

        Tuple2<String, Integer> that = java8.map(
                (s, i) -> Tuple.of(s.substring(2) + "vr", i / 8)
        );
        System.out.println(that );
    }

    public static void functionTest(){
        // sum.apply(1, 2) = 3
        Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;

        Function2<Integer, Integer, Integer> sum2 = new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer a, Integer b) {
                return a + b;
            }
        };
    }

    public static void compositionTest(){
        Function1<Integer, Integer> plusOne = a -> a + 1;
        Function1<Integer, Integer> multiplyByTwo = a -> a * 2;

        Function1<Integer, Integer> add1AndMultiplyBy2 = plusOne.andThen(multiplyByTwo);

        System.out.println(add1AndMultiplyBy2.apply(2));

        //then(add1AndMultiplyBy2.apply(2)).isEqualTo(6);
        Function1<Integer, Integer> add1AndMultiplyBy2_ = multiplyByTwo.compose(plusOne);
        System.out.println(add1AndMultiplyBy2_.apply(2));
    }

    public static void LiftingTest(){
        Function2<Integer, Integer, Integer> divide = (a, b) -> a / b;
        Function2<Integer, Integer, Option<Integer>> safeDivide = Function2.lift(divide);
        Option<Integer> i1 = safeDivide.apply(1, 0);
        Option<Integer> i2 = safeDivide.apply(4, 2);
        System.out.println(i1);
        System.out.println(i2);

        Function2<Integer, Integer, Option<Integer>> sum = Function2.lift(BasicUseApp::sum);
        Option<Integer> optionalResult = sum.apply(-1, 2);
        System.out.println(optionalResult);
    }

    public static int  sum(int first, int second) {
        if (first < 0 || second < 0) {
            throw new IllegalArgumentException("Only positive integers are allowed");
        }
        return first + second;
    }

    public static void PartialApplicationTest(){
        Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;
        Function1<Integer, Integer> add2 = sum.apply(2);
        System.out.println(add2.apply(4));

        Function5<Integer, Integer, Integer, Integer, Integer, Integer> sum5 = (a, b, c, d, e) -> a + b + c + d + e;
        Function2<Integer, Integer, Integer> add6 = sum5.apply(2, 3, 1);

        System.out.println(add6.apply(4, 3));//(add6.apply(4, 3)).isEqualTo(13);
    }

    public static void curryTest(){
        Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;
        Function1<Integer, Integer> add2 = sum.curried().apply(2);

        //then(add2.apply(4)).isEqualTo(6);
        System.out.println(add2.apply(4));

        Function3<Integer, Integer, Integer, Integer> sum3 = (a, b, c) -> a + b + c;
        final Function1<Integer, Function1<Integer, Integer>> add3 = sum3.curried().apply(2);

        //then(sum3.apply(4).apply(3)).isEqualTo(9);
        System.out.println(add3.apply(4).apply(3));

        System.out.println(sum3.curried().apply(2).apply(4).apply(3));

    }

    public static void memorizationTest(){
        Function0<Double> hashCache =
                Function0.of(Math::random).memoized();

        double randomValue1 = hashCache.apply();
        double randomValue2 = hashCache.apply();

        //then(randomValue1).isEqualTo(randomValue2);

        System.out.println(randomValue1==randomValue2);
    }
}
