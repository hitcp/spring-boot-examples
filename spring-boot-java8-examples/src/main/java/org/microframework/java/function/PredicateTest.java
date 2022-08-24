package org.microframework.java.function;

import java.util.function.Predicate;

public class PredicateTest {
    public static Predicate<Integer> predicate = s -> {
        if (s > 5) {
            return true;
        }
        return false;
    };

    public static void main(String[] args) {
        System.out.println(predicate.test(5));
    }


}
