package com.bty.oa.organization.service.impl;

import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;
/**
 * @author Shaoyu Liu
 * @date 2023/6/29
 */
public class AllFunctionTest {
    public static void main(String[] args) {
        /* 一、基本函数
           1、Function; BiFunction
           2、Consumer; BiConsumer
           3、Predicate; BiPredicate
           4、Supplier */

        /* 1.1 入参：对象；返回：对象R
           1.2 入参：对象1，对象2；返回：对象R  */
        Function<Object, Object> function = s -> null;
        BiFunction<Object, Object, Object> biFunction = (s, t) -> null;

        /* 2.1 入参：对象；返回：无
           2.2 入参：对象1，对象2；返回：无 */
        Consumer<Object> consumer = s -> {};
        BiConsumer<Object, Object> biConsumer = (s, t) -> {};

        /* 3.1 入参：对象；返回：布尔值
           3.2 入参：对象1，对象2；返回：布尔值 */
        Predicate<Object> predicate = s -> true;
        BiPredicate<Object, Object> biPredicate = (s, t) -> true;

        /* 4.1 入参：方法引用；返回：无 ( this::getObjectMethod)*/
        Supplier<Object> supplier = Object::new;



        /* 二、进阶函数
           1、UnaryOperator 继承 Function
           2、BinaryOperator 继承  BiFunction  */

        /* 1. 入参：对象；返回：原对象 */
        UnaryOperator<Object> unaryOperator = s -> s;

        /* 1. 入参：对象1，对象2；返回：对象1或对象2  */
        BinaryOperator<Object> binaryOperator = (s, t) -> s;

    }
}
