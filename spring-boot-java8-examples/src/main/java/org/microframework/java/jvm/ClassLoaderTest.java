package org.microframework.java.jvm;

public class ClassLoaderTest {
//    static {
//        System.out.println("=============ClassLoaderTest============");
//    }

    public static void main(String[] args) {
//        new A();
//        System.out.println("=============load test============");
//        B b = null; //B不会加载，除非实例化 new B()
        System.out.println(String.class.getClassLoader());
        // JAVA_HOME\jre\lib\ext\sunjce_provider.jar
        System.out.println(com.sun.crypto.provider.DESKeyFactory.class.getClassLoader());
        System.out.println(ClassLoaderTest.class.getClassLoader());
    }
}


class A {
    static {
        System.out.println("=============load A============");
    }

    public A() {
        System.out.println("=============initial A============");
    }
}

class B {
    static {
        System.out.println("=============load B============");
    }

    public B() {
        System.out.println("=============initial B============");
    }
}