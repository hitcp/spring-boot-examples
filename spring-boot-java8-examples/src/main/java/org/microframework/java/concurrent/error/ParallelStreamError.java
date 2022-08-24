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
//        method2();
        // 现象3：数组中有null对象[未复现]
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
