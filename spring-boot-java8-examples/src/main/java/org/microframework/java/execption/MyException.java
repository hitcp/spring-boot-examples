package org.microframework.java.execption;

/**
 * @author Shaoyu Liu
 * @date 2022-10-26
 */
public class MyException extends RuntimeException {
    public MyException(String error) {
        super(error);
    }
}
