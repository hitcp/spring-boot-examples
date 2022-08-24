package org.microframework.java.concurrent.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * 多线程并发同时执行
 * <p>
 * 1.通过CountDownLatch来实现
 * 2.通过CyclicBarrier来实现
 *
 * @author Shaoyu Liu
 * @date 2021/5/29 22:54
 * @see #latch
 * @see #barrier
 **/
public class Concurrent {


    public static void main(String[] args) {
//        try {
//            latch();
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        barrier();

    }


    /**
     * 1.通过CountDownLatch来实现多线程同时执行
     */
    public static void latch() throws InterruptedException {
        // 线程量规模
        int threadCount = 3;
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                    System.out.println(Thread.currentThread().getName() + "执行时间：" + System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        // 所有线程准备开始所需消耗时间
        System.out.println("所有线程准备开始所需消耗时间:"+ countDownLatch.getCount());
        Thread.sleep(200);
        countDownLatch.countDown();
    }

    /**
     * 2.通过CyclicBarrier来实现多线程同时执行
     */
    public static void barrier() {
        // 线程量规模
        int threadCount = 3;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(threadCount, () -> {
            // 1）会先执行barrierAction中的方法
            System.out.println(Thread.currentThread().getName() + "执行时间：" + System.currentTimeMillis());
        });
        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                try {
                    //阻塞线程等待所有线程就绪
                    cyclicBarrier.await();
                    // 2）执行等待的线程
                    System.out.println(Thread.currentThread().getName() + " 开始时间：" + System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }


}
