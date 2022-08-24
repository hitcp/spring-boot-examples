package org.microframework.java.generic;

/**
 * 泛型类
 *
 * @param <T>
 */
public class GenericClass<T> {
    private T key;

    public GenericClass(T key) {
        this.key = key;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }
}