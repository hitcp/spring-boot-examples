package org.microframework.webflux.entity;

/**
 * @author Shaoyu Liu
 * @date 2022-08-18
 */
public class User {
    private Integer Id;
    private String name;

    public User(String name) {
        this.name = name;
    }

    public User(Integer id) {
        Id = id;
    }

    public User(Integer id, String name) {
        Id = id;
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

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }
}