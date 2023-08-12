package org.microframework.java.util.validate;

/**
 * @author Shaoyu Liu
 * @date 2023/8/12
 */
public class Test {
    public static void main(String[] args) {
        User user = new User();
        System.out.println(ValidateUtils.validate(user));
    }
}
