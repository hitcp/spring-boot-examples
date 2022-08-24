package org.microframework.java.new2;

/**
 * 匿名内部类
 *
 * @author Shaoyu Liu
 * @date 2022/3/24 17:08
 */
public class AnonymousClassTest {
    public static void main(String[] args) {
        AnonymousClass anonymousClass = new AnonymousClass();
    }
}

class AnonymousClass {
    public AnonymousClass() {
        System.out.println("执行匿名内部类构造函数");
    }

    static {
        System.out.println("执行匿名内部类静态代码块");
    }
}