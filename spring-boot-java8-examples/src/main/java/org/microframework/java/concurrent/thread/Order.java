package org.microframework.java.concurrent.thread;

import java.util.concurrent.CyclicBarrier;

/**
 * 多线程并发依次执行
 * <p>
 * 1.通过CyclicBarrier实现
 * 2.通过Semaphore实现
 * 3.通过ReentrantLock实现
 * 4.通过synchronized关键字实现
 * 5.通过CountDownLatch实现（只可执行1次）
 *
 * @author Shaoyu Liu
 * @date 2021/5/29 23:30
 * @see 1.#barrier
 **/
public class Order {

    public static void main(String[] args) {
        barrier();
    }

    /**
     * 线程量规模
     */
    private static volatile int threadCount = 3;


    /**
     * 1.通过CyclicBarrier实现多线程依次执行
     */
    public static void barrier() {
        CyclicBarrier barrier1 = new CyclicBarrier(2);
        CyclicBarrier barrier2 = new CyclicBarrier(2);
        CyclicBarrier barrier3 = new CyclicBarrier(2);

        new Thread(new barrierTask("A", barrier3, barrier1)).start();
        new Thread(new barrierTask("B", barrier1, barrier2)).start();
        new Thread(new barrierTask("C", barrier2, barrier3)).start();
    }

    private static class barrierTask implements Runnable {
        // 用来判断开始执行的线程是哪一个
        private boolean first = true;

        private String name;


        // 如不该自己执行，那么就等待
        private CyclicBarrier awaitBarrier;
        // 执行完成后，通知下一节点
        private CyclicBarrier notifyBarrier;

        public barrierTask(String name, CyclicBarrier awaitBarrier, CyclicBarrier notifyBarrier) {
            this.name = name;
            this.awaitBarrier = awaitBarrier;
            this.notifyBarrier = notifyBarrier;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    if ("A".equals(name) && first) {
                        first = false;
                    } else {
                        awaitBarrier.await();
                    }
                    System.out.println(name + "  " + threadCount++);
                    notifyBarrier.await();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
