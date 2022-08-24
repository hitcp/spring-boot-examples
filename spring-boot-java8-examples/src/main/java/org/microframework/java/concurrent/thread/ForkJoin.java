package org.microframework.java.concurrent.thread;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @author Shaoyu Liu
 * @date 2021/5/30 23:01
 **/
public class ForkJoin {
    public void ForkJoinPool() {
        ForkJoinTask forkJoinTask = new ForkJoinTask() {
            @Override
            public Object getRawResult() {
                return null;
            }

            @Override
            protected void setRawResult(Object value) {

            }

            @Override
            protected boolean exec() {
                return false;
            }
        };
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.execute(forkJoinTask);
    }
}
