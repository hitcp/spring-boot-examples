package org.microframework.java.generic;

/**
 * 泛型接口
 * @author Shaoyu Liu
 * @date 2022-08-17
 */

public interface GenericInterfaceExample {

}

class GenericInterfaceExampleImpl implements GenericInterfaceExample {
    /**
     * 其中 <T> 是声明类型，如果类上不声明就必须在方法权限修饰符后面声明；如果类上声明权限修饰符后的声明则可省略
     *
     * @param <T>
     * @return
     */
    private <T> ThreadLocal<T> execute() {
        return new ThreadLocal<>();
    }
}


interface GenericInterfaceExample2<T> {
}

class GenericInterfaceExampleImpl2<T> implements GenericInterfaceExample2<T> {
    /**
     * 其中 <T> 是声明类型，如果类上不声明就必须在方法权限修饰符后面声明；如果类上声明权限修饰符后的声明则可省略
     *
     * @return
     */
    private ThreadLocal<T> execute() {
        return new ThreadLocal<>();
    }
}

class GenericInterfaceExampleImpl3 implements GenericInterfaceExample2<User> {
    /**
     * 其中 <T> 是声明类型，如果类上不声明就必须在方法权限修饰符后面声明；如果类上声明权限修饰符后的声明则可省略
     *
     * @return
     */
    private ThreadLocal<User> execute() {
        return new ThreadLocal<>();
    }
}

class User{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


