package org.microframework.java.bean;

/**
 * @author Shaoyu Liu
 * @date 2021/11/10 17:21
 **/
@FunctionalInterface
public interface IBeanUtilCallBack<S, T> {
    /**
     * 定义默认回调方法
     *
     * @param s
     * @param t
     */
    void callBack(S s, T t);
}
