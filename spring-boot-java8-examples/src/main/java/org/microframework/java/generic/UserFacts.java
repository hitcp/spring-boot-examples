package org.microframework.java.generic;//package com.liushaoyu.springcloud.generic优先级别1;
//
//public class UserFacts {
//    public UserFacts(String name, Integer age) {
//        this.name = name;
//        this.age = age;
//    }
//
//    public UserFacts() {
//    }
//
//    private String name;
//
//    private Integer age;
//
//    public static UserFactsBuilder builder() {
//        return new UserFactsBuilder();
//    }
//
//    public static class UserFactsBuilder implements BuilderInterface<UserFacts> {
//
//        private String name;
//
//        private Integer age;
//
//        public UserFactsBuilder name(String name) {
//            this.name = name;
//            return this;
//        }
//
//        public UserFactsBuilder age(Integer age) {
//            this.age = age;
//            return this;
//        }
//
//        @Override
//        public UserFacts build() {
//            return new UserFacts(this);
//        }
//    }
//
//    private UserFacts(UserFactsBuilder builder) {
//        this.name = builder.name;
//        this.age = builder.age;
//    }
//
//    @Override
//    public String toString() {
//        return "UserFacts{" +
//                "name='" + name + '\'' +
//                ", age=" + age +
//                '}';
//    }
//}