package org.microframework.java.concurrent.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程并发有序交错执行
 * <p>
 * 1.通过Semaphore实现
 * 2.通过ReentrantLock实现
 *
 * @author Shaoyu Liu
 * @date 2021/5/30 0:07
 * @see 1.#semaphore
 * @see 2.#reentrant
 **/
public class Alternate {

    public static void main(String[] args) {
//        semaphore();
        reentrant();
    }

    /**
     * 1.通过Semaphore实现多线程并发有序交错执行
     */
    public static void semaphore() {
        // 设置信号量为1
        Semaphore semaphore1 = new Semaphore(1);
        Semaphore semaphore2 = new Semaphore(1);
        Semaphore semaphore3 = new Semaphore(1);

        int threadCount = 10;

        try {
            //获取信号量，当被获取后不释放将会卡住
            semaphore2.acquire();
            semaphore3.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        semaphoreThread(semaphore1, semaphore2, threadCount).start();
        semaphoreThread(semaphore2, semaphore3, threadCount).start();
        semaphoreThread(semaphore3, semaphore1, threadCount).start();
    }

    private static Thread semaphoreThread(Semaphore acquireSemaphore, Semaphore releaseSemaphore, int threadCount) {
        return new Thread(() -> {
            for (int i = 0; i < threadCount; i++) {
                try {
                    acquireSemaphore.acquire();
                    System.out.println("执行线程" + Thread.currentThread().getName() + "业务");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                releaseSemaphore.release();
            }
        });
    }

    /**
     * 2.通过Semaphore实现多线程并发有序交错执行
     */
    public static void reentrant() {
        final ReentrantLock lock = new ReentrantLock();
        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();
        Condition c3 = lock.newCondition();
        //解题思路：使用显示锁
        reentrantThread(lock, c2, c1).start();
        reentrantThread(lock, c3, c2).start();
        reentrantThread(lock, c1, c3).start();
    }

    private static Thread reentrantThread(ReentrantLock lock, Condition signalCondition, Condition awaitCondition) {
        return new Thread(() -> {
            try {
                lock.lock();
                System.out.println("lock...");
                for (int i = 0; i < 10; i++) {
                    System.out.println("执行线程" + Thread.currentThread().getName() + "业务");
                    signalCondition.signal();
                    awaitCondition.await();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("unlock...");
            }
        });
    }
}
