package org.microframework.java.function;

/**
 * @author Shaoyu Liu
 * @date 2021/12/23 11:27
 **/
@FunctionalInterface
public interface BranchHandle {
    /**
     * true false 操作
     *
     * @param trueHandle  为true时要进行的操作
     * @param falseHandle 为false时要进行的操作
     * @return void
     **/
    void trueOrFalseHandle(Runnable trueHandle, Runnable falseHandle);

}
