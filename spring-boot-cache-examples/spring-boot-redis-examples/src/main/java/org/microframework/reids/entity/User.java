package org.microframework.reids.entity;

/**
 * @author Shaoyu Liu
 * @date 2022-08-27
 */
public class User {
    private Long id;
    private String name;
    private String account;

    public User() {
    }

    public User(Long id, String name, String account) {
        this.id = id;
        this.name = name;
        this.account = account;
    }

    public User(String name, String account) {
        this.name = name;
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", account='" + account + '\'' +
                '}';
    }
}
