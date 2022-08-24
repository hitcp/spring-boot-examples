package org.microframework.java.function;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * 只提供一个入值
 */
public class ConsumerTest {
    private static final Consumer<String> consumer = s -> System.out.println("consumer:" + s);

    public static void main(String[] args) {
        List<String> stringList = Arrays.asList("a", "b", "c");
        stringList.forEach(consumer);
    }
}
