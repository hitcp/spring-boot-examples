package org.microframework.java.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 锁（jdk级别）
 *
 * @author Shaoyu Liu
 * @date 2022/4/7 23:03
 */
public class MyLock implements Lock {

    @Override
    public void lock() {
        // TODO
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        // TODO
    }

    @Override
    public Condition newCondition() {
        throw new UnsupportedOperationException();
    }
}
