package org.microframework.basis.conditional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Shaoyu Liu
 * @date 2022-08-15
 */
@Configuration
public class ConditionalOnBeanExample {
    @Bean
    public Computer2 defaultComputer2() {
        return new Computer2("ThinkPad");
    }

    /**
     * 1.在指定bean基础上实现自己的逻辑，与@ConditionalOnMissingBean注解功能相反，
     * 判断IOC容器中Computer对应的bean是否存在 :如果容器中存在Computer就执行（“备用电脑”），不存在就执行（“备用电脑”）<p>
     * 2.如果依赖的bean不存在则抛出 NoSuchBeanDefinitionException
     */
    @Bean
    @ConditionalOnBean(Computer2.class)
    public BakComputer2 bakComputer2() {
        return new BakComputer2("备用电脑");
    }
}

class Computer2 {
    private String name;

    public Computer2(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class BakComputer2 {
    private String name;

    public BakComputer2(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}