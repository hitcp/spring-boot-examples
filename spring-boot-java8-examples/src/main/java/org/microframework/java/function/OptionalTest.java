package org.microframework.java.function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * 使用 Optional 时
 * 尽量不直接调用 Optional.get() ， Optional.isPresent() 更应该被视为一个私有方法,
 * 应依赖于其他像 Optional.orElse(), Optional.orElseGet(), Optional.map() 等这样的方法.
 *
 * @author Shaoyu Liu
 * @date 2023/6/17 20:39
 */
public class OptionalTest {
    
    public void main(String[] args) {
        Optional<String> stringOptional = Optional.ofNullable(null);

        // 1.（过滤） 符合条件取原值，不符合条件取orElse
        System.out.println(stringOptional.filter((value) -> value.length() > 10).orElse("The name is less than 6 characters"));

        // 2.（映射）map映射原来对象成新对象；flatMap映射原来对象成新对象（返回必须用Optional类型）
        System.out.println(stringOptional.map(String::toLowerCase).get());
        System.out.println(stringOptional.flatMap(s -> Optional.of(s.toLowerCase())).get());

        // 3.对象构造 Optional.of(obj)，Optional.ofNullable(obj)，Optional.empty()
        // 3.1存在即返回，不存在通过指定函数获取
        System.out.println(stringOptional.orElseGet(OptionalTest::generateMethod));// ()-> generateMethod()
        // 3.2存在即返回，不存在给定默认值
        System.out.println(stringOptional.orElse("默认值"));
        // 3.3存在就执行Consumer函数
        stringOptional.ifPresent(System.out::println);
        
        
        // 嵌套取值
//        name = Optional.ofNullable(atest).map(t->t.getBtest()).map(t->t.getCtest()).map(t->t.getName()).orElse("默认值");
        // 原理如下：
//        if(atest!=null){
//            Btest btest =atest.getBtest();
//            if(btest!=null){
//                Ctest ctest = btest.getCtest();
//                if (ctest != null) {
//                    name =ctest.getName();
//                }
//            }
//        }

    }
    
    
    private static String generateMethod(){
        return "strGenerateMethod";
    }
    
    
    private class User{
        private String string1;
        private User2 child;
        
        public String getString1() {
            return string1;
        }
        
        public void setString1(String string1) {
            this.string1 = string1;
        }
        
        public User2 getChild() {
            return child;
        }
        
        public void setChild(User2 child) {
            this.child = child;
        }
    }
    
    private class User2{
        private String string2;
        private User3 child;
        
        public String getString2() {
            return string2;
        }
        
        public void setString2(String string2) {
            this.string2 = string2;
        }
        
        public User3 getChild() {
            return child;
        }
        
        public void setChild(User3 child) {
            this.child = child;
        }
    }
    
    private class User3{
        private String name;
        
        public String getName() {
            return name;
        }
        
        public void setName(String name) {
            this.name = name;
        }
    }
    
}
