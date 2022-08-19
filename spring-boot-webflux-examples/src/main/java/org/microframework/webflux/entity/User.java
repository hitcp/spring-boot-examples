package org.microframework.webflux.entity;

/**
 * @author Shaoyu Liu
 * @date 2022-08-18
 */
public class User {
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
}