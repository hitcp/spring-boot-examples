package org.microframework.mq.rocketmq;

import java.io.Serializable;

/**
 * @author Shaoyu Liu
 * @date 2022-12-05
 */
public class User implements Serializable {
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
}
