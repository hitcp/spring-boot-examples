package org.microframework.java.lambda;

import org.microframework.java.function.PredicateTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaTest {
    public static void main(String[] args) {
        Stream<Integer> stream = Stream.of(1, 23, 3, 4, 5, 56, 6, 6);
        List<Integer> list = stream.filter(PredicateTest.predicate).collect(Collectors.toList());
         
        System.out.println(Arrays.asList(list.toArray()));

    }
}
