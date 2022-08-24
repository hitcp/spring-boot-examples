package org.microframework.java.concurrent.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ThreadPoolTest {
    /**
     * 线程池开启线程
     * 强制：线程池不允许使用 Executors 去创建，而是通过 ThreadPoolExecutor 的方式，
     * 这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。
     * 说明：Executors 返回的线程池对象的弊端如下：
     * 1）FixedThreadPool 和 SingleThreadPool:允许的请求队列长度为 Integer.MAX_VALUE，可能会堆积大量的请求，从而导致 OOM。
     * 2）CachedThreadPool 和 ScheduledThreadPool:允许的创建线程数量为 Integer.MAX_VALUE，可能会创建大量的线程，从而导致 OOM。
     * ScheduledExecutorService是ExecutorService子类,增加了定时
     */
    ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(20);
    ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
    ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
    ExecutorService newWorkStealingPool = Executors.newWorkStealingPool();

    ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(20);
    ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();

//    ExecutorService executorService;
//    ExecutorService unconfigurableExecutorService = Executors.unconfigurableExecutorService(executorService);
//    ScheduledExecutorService scheduledExecutorService;
//    ScheduledExecutorService unconfigurableScheduledExecutorService = Executors.unconfigurableScheduledExecutorService(scheduledExecutorService);
    /**
     * 手动创建线程
     */
    Thread thread;

    /**
     * 不能返回执行结果，相比callable速度快一点
     */
    Runnable runnable;

    /**
     * 可以返回执行结果，相比runnable速度慢一点
     */
    Callable callable;


    public void execute() {

        // 1. 手动创建线程
        new Thread().start();
        // 2.不能返回执行结果，相比callable速度快一点
        runnable.run();
        // 3.可以返回执行结果，相比runnable速度慢一点
        try {
            callable.call();
        } catch (Exception e) {
            // 无法计算callable结果
            e.printStackTrace();
        }
        // 4.线程池
        newFixedThreadPool.execute(() -> {
            // TODO
        });
//        newScheduledThreadPool.schedule()
    }
}
