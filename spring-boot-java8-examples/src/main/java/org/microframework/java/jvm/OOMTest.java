package org.microframework.java.jvm;

/**
 * @author Shaoyu Liu
 * @date 2022/3/25 20:06
 */
public class OOMTest {
    public static void main(String[] args) {
        oom();
    }

    private static void oom(){
        while (true) {
            Student student = new Student();
        }
    }
    static class Student{
        char[] chars = new char[2048*100];
    }
}
