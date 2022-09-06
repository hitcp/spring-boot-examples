package org.microframework.java.serializable;

import java.io.Serializable;

/**
 * Hessian和jdk序列化的对象都必须实现序列化接口，否则：  Exception in thread "main" java.io.NotSerializableException: org.microframework.java.serializable.User
 *
 * @author Shaoyu Liu --- website: <a href="http://hitcp.cn">Hi TCP</a>
 * @date 2022-09-06
 */
class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public User() {
    }
}