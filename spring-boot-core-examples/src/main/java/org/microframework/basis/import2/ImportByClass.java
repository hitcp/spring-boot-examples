package org.microframework.basis.import2;

/**
 * @author Shaoyu Liu
 * @date 2022-08-23
 */
public class ImportByClass {
    static {
        System.out.println("ImportByClass init...");
    }

    public void foo() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getClassName());
    }
}
