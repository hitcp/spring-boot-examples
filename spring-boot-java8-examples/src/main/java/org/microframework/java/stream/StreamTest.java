package org.microframework.java.stream;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class StreamTest {
    private static volatile int state;
    private static final Object OBJECT_LOCK = new Object();
    public static AtomicInteger ATOMIC_LOCK = new AtomicInteger(0);
    public static volatile int singleton;
    private static int DATA_SIZE = 500000;


    public static void main(String[] args) throws IOException {
        List<String> keywordSet = new ArrayList<>();
        for (int i = 0; i < DATA_SIZE; i++) {
            keywordSet.add("keywordStr"+i);
        }

        AtomicInteger keywordCount = new AtomicInteger(0);
        keywordSet.parallelStream().forEach(keyword -> {
            if (StringUtils.contains(keyword, "keywordStr")) {
                keywordCount.incrementAndGet();
            }
        });
        System.out.println("包含数量："+keywordCount.get());

        List<Integer> numbers = new ArrayList<>();// Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)
        for (int i = 0; i < DATA_SIZE; i++) {
            numbers.add(i);
        }
        // 单线程流，无并发问题
        long streamStart = System.currentTimeMillis();
        List<Integer> numbers1 = new ArrayList<>();
        numbers.stream().forEach(number -> {
                    numbers1.add(number);
                }
//            System.out.println(Thread.currentThread().getName() + ">>" + number)
        );
        System.out.println(System.currentTimeMillis() - streamStart);
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


        /*
        并发流，集合安全需要手动实现,
        parallelStream + Object锁 50万数据以上会和stream效率相同
         */
        long parallelStreamStart = System.currentTimeMillis();


        /*   一、添加元素发生越界异常
        在并发情况下，如果同时有A、B两个线程同时执行add，在第一步ensureCapacityInternal校验数组容量时，
        A、B线程都发现当前容量还可以添加最有一个元素，不需扩容；
        因此进入第二步，此时，A线程先执行完，数组容量已满，然后B线程再对elementData赋值时，就会抛出“ArrayIndexOutOfBoundsException”
        解决方式：
            1.使用stream list或foreach(迭代器模式)
            2.使用CopyOnWriteArrayList
            3.加锁
         */
        List<Integer> numbers2 = new ArrayList<>();

        numbers.parallelStream().forEach(number -> {
                    // 1.Object锁
//                    synchronized (lock) {
//                        numbers2.add(number);
//                    }
                    // 2.class锁
                    synchronized (StreamTest.class) {
                        numbers2.add(number);
                    }
                    // 3.
//                    if (ATOMIC_LOCK.get() == 0) {
//                        synchronized (OBJECT_LOCK){
//                            ATOMIC_LOCK.incrementAndGet();
//                            numbers2.add(number);
//                            ATOMIC_LOCK.decrementAndGet();
//                        }
//                    }


                }
//                System.out.println(Thread.currentThread().getName() + ">>" + number)
        );
        System.out.println("numbers2 size:" + numbers2.size());
        System.out.println(System.currentTimeMillis() - parallelStreamStart);
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
