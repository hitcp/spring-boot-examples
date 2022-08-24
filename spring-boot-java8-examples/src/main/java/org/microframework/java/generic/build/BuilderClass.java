package org.microframework.java.generic.build;

public class BuilderClass {
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

    @Override
    public String toString() {
        return "BuilderClass{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
