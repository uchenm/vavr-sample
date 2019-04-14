package com.craft;

import io.vavr.Lazy;

public class LazyApp {

    public static void main(String[] args) {
        Lazy<Double> lazy = Lazy.of(Math::random);
        lazy.isEvaluated(); // = false
        lazy.get();         // = 0.123 (random generated)
        lazy.isEvaluated(); // = true
        lazy.get();         // = 0.123 (memoized)

        CharSequence chars = Lazy.val(() -> "Yay!", CharSequence.class);
        System.out.println(chars);
    }
}
