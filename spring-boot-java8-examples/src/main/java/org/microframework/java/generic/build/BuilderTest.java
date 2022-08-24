package org.microframework.java.generic.build;

import java.util.Optional;

public class BuilderTest {
    public static void main(String[] args) {
//        UserFacts userFacts = new UserFacts();
//        UserFacts userFactsBuild = UserFacts.builder().name("小明").age(20).build();
//        System.out.println(userFactsBuild);
        BuilderClass builderClass= null;
        BuilderClass builderClass1 = Optional.ofNullable(builderClass).orElse(null);
        Integer age = builderClass1.getAge();
    }
}
