package org.microframework.basis.conditional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Shaoyu Liu
 * @date 2022-08-15
 */
@Configuration
public class ConditionalOnMissingBeanExample {

    @Bean
    public Computer defaultComputer() {
        return new Computer("ThinkPad");
    }

    /**
     * 判断IOC容器中Computer对应的bean是否存在 :如果容器中不存在就执行（“备用电脑”），存在Computer则执行（“ThinkPad”）
     */
    @Bean
    @ConditionalOnMissingBean(Computer.class)
    public Computer bakComputer() {
        return new Computer("备用电脑");
    }
}

class Computer {
    private String name;

    public Computer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}