package org.microframework.java.jvm;

/**
 * @author Shaoyu Liu
 * @date 2022/3/30 13:48
 */
public class T {
    public static void main(String[] args) {
        cpuHigh();
        threadDealLock();

    }

    /**
     * 模拟cpu飙高
     */
    public static void cpuHigh() {
        new Thread(() -> {
            while (true) {
            }
        }).start();
        new Thread(() -> {
            while (true) {
            }
        }).start();
        new Thread(() -> {
            while (true) {
            }
        }).start();
        new Thread(() -> {
            while (true) {
            }
        }).start();
    }

    /**
     * 模拟线程死锁
     */
    public static void threadDealLock() {
        final Object lock1 = new Object();
        final Object lock2 = new Object();
        new Thread(() -> {
            synchronized (lock1) {
                System.out.println("线程1开始加锁");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println(1);
                }
            }
        }).start();
        new Thread(() -> {
            synchronized (lock2) {
                System.out.println("线程2开始加锁");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println(2);
                }
            }
        }).start();
    }
}
