package org.microframework.java.concurrent.lock;


import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

public class LockTest {

    private final ReentrantLock reentrantLock = new ReentrantLock();

    private final StampedLock stampedLock = new StampedLock();

    public void execute() {
        reentrantLock.lock();  // block until condition holds
        // 是一种规范，而不是强制的，为了防止lock方法放在try里面有问题执行unlock()方法，unlock方法里面的tryRelease方法会比较这个独占锁线程是否和当前释放锁的线程是同一个线程，如果不是就抛出异常（因为lock前异常所以这个独占锁没有所属线程）
        try {
            // ... method body
        } finally {
            reentrantLock.unlock();
        }


        // 1.乐观读
        long readLock = stampedLock.tryOptimisticRead();
        // TODO step1
        // 独占锁：false，非独占锁：true（判断乐观读后TODO step1操作是否有写锁发生）
        if (stampedLock.validate(readLock)) {
            // 悲观读
            readLock = stampedLock.readLock();
            // 解锁
            stampedLock.unlockRead(readLock);
        }

        // 2.悲观读
        long readLock2 = stampedLock.readLock();
        try {
            // TODO
        }finally {
            stampedLock.unlockRead(readLock2);
        }

        // 1.悲观写（排它写）
        long writeLock = stampedLock.writeLock();
        try {
            // TODO
        }finally {
            stampedLock.unlockWrite(writeLock);
        }


    }
}
