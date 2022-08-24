package org.microframework.basis.conditional;

import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author Shaoyu Liu
 * @date 2022-08-15
 */
@Configuration
public class ConditionExample {
    /**
     * 当matches方法返回true才会执行此bean注册，返回false则ioc容器不进行此bean的注册，使用@Autowired时候抛出NoSuchBeanDefinitionException异常
     *
     * @return
     */
    @Bean("windows")
    @Conditional(WindowsCondition.class)
    public ServerSystem windowsSystem() {
        return new ServerSystem("Windows server system");
    }

    /**
     * 当matches方法返回true才会执行此bean注册，返回false则ioc容器不进行此bean的注册，使用@Autowired时候抛出NoSuchBeanDefinitionException异常
     *
     * @return
     */
    @Bean("linux")
    @Conditional(LinuxCondition.class)
    public ServerSystem linuxSystem() {
        return new ServerSystem("Linux server system");
    }


//    public static void main(String[] args) {
//        AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(ConditionExample.class);
//        String[] beanDefinitionNames = ioc.getBeanDefinitionNames();
//        for (String beanDefinitionName : beanDefinitionNames) {
//            System.out.println(beanDefinitionName);
//        }
//    }

}

class ServerSystem {
    private String name;

    public ServerSystem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class WindowsCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return false;
    }
}

class LinuxCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return true;
    }
}


