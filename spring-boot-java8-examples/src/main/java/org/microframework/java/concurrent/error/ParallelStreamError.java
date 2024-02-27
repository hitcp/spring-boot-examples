package org.microframework.java.concurrent.error;

import java.util.ArrayList;
import java.util.List;

/**
 * 问题描述：parallelStream 多次运行会出现ArrayIndexOutOfBoundsException，出现次数不一定
 * <p>
 * 现象1：ArrayIndexOutOfBoundsException
 * 现象2：数组会变多或变少
 * 现象3：数组中有null对象
 * <p>
 * 解决办法：使用用线程安全的CopyOnWriteArrayList或者使用stream()
 * <p>
 * https://blog.csdn.net/qq_23893575/article/details/125442975
 *
 * @author Shaoyu Liu
 * @date 2022-08-17
 */
public class ParallelStreamError {
    /**
     * 现象1：ArrayIndexOutOfBoundsException
     *
     * @param args
     */
    public static void main(String[] args) {
        // 现象1：ArrayIndexOutOfBoundsException，可能需要多次运行
//        method1();
        // 现象2：数组会变多或变少
        /*        导致数组下标越界的原因是 ArrayList 的 add() 方法中的 elementData[size++] = e，这行代码不是原子操作，可以拆解为：
        读取 size 值
        将 e 添加到 size 的位置，即 elementData[size] = e
        size++
        这里存在内存可见性问题，当线程 A 从内存读取 size 后，设置 e 值，将 size 加 1，然后写入内存。过程中可能有线程 B 也修改了 size 并写入内存，
        那么线程 A 写入内存的值就会丢失线程 B 的更新。这解释了会出现数组长度比原始数组要小（元素丢失）的情况。*/
//        method2();


        // 现象3：数组中有null对象[未复现]
        /*
        null 元素产生跟元素数据丢失类似，也是由于 elementData[size++] = e 不是原子操作导致的。假设存在三个线程，线程 1、线程 2、线程 3。三个线程同时开始执行，初始 size 值为 1。

        线程 1 全部执行完毕，此时 size 被更新为 2。

        线程 2 一开始读取 size 值 = 1、将 e 添加到 size 位置后时间片就用完了，轮到执行第三步 size++ 读取到了线程 1 的更新，size 直接被更新成了 3。【注：此处线程 2 的 e 值也丢失了，被线程 1 覆盖】

        线程3 一开始读取 size 值 = 1 后时间片就用完了，轮到执行第二步将 e 添加到 size 位置读取到了线程 2 的更新，size 变成了 3。size = 2 的位置就被跳过了，因此 elementData[2] 为 null 了。
        */
    }

    private static void method1() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            User user = new User();
            user.setName("张三" + i);
            users.add(user);
        }
        List<User> users2 = new ArrayList<>();
        // parallelStream 多次运行会出现，出现次数不一定 Caused by: java.lang.ArrayIndexOutOfBoundsException: 823
        // 解决办法：list用线程安全的CopyOnWriteArrayList或者使用stream()
        users.parallelStream().forEach(s -> {
            User user = new User();
            user.setName(s.getName());
            users2.add(user);
        });
        users.forEach(System.out::println);
    }

    private static void method2() {
        List<User> users = new ArrayList<>(1000);
        List<User> users2 = new ArrayList<>(1000);
        for (int i = 0; i < 1000; i++) {
            users2.add(new User("小明" + i));
        }
        users2.parallelStream().forEach(s -> {
            users.add(new User(s.getName()));
        });
        System.out.println(users.size());

        //  现象3：数组中有null对象[未复现]
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user == null) {
                System.out.println("出现空对象对象！");
            }
        }
    }
}


class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
