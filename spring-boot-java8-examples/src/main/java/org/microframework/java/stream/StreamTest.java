package org.microframework.java.stream;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) throws IOException {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        // 单线程流，无并发问题
        numbers.stream().forEach(number -> System.out.println(Thread.currentThread().getName() + ">>" + number));
        /*
        控制台输出：
        main>>1
        main>>2
        main>>3
        main>>4
        main>>5
        main>>6
        main>>7
        main>>8
        main>>9
         */
        // 并发流，集合安全需要手动实现
        numbers.parallelStream().forEach(number -> System.out.println(Thread.currentThread().getName() + ">>" + number));
        /*
        控制台输出：
        main>>6
        main>>5
        main>>2
        ForkJoinPool.commonPool-worker-11>>1
        main>>7
        ForkJoinPool.commonPool-worker-11>>4
        ForkJoinPool.commonPool-worker-13>>9
        ForkJoinPool.commonPool-worker-9>>3
        ForkJoinPool.commonPool-worker-2>>8
        */
    }
}
