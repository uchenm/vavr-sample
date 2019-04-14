package com.craft;

import io.vavr.control.Either;

public class EitherApp {

    public static void main(String[] args) {
        Either<String,Integer> value = devide(10,2).map(i -> (int)i*2);
        System.out.println(value);
        Either<String,Integer> value2 = devide(10,0).map(i -> (int)i*2);
        System.out.println(value2);
    }

    public static Either devide(int x, int y){
        if(y==0) return Either.left("Dude, cant' devide by 0");
        else return Either.right(x/y);
    }
}
