package org.microframework.mq.rocketmq;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Shaoyu Liu
 * @date 2022-12-05
 */
@Component
public class Producer implements CommandLineRunner {

    @Resource
    private RocketMQTemplate rocketMQTemplate;


    @Override
    public void run(String... args) throws Exception {
        rocketMQTemplate.convertAndSend("test-topic-1", "Hello, World!");
        rocketMQTemplate.convertAndSend("test-topic-2", new User("张三", 55));
    }

}

