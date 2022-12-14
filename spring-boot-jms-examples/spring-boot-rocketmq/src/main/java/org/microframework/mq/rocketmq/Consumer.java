package org.microframework.mq.rocketmq;

import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * @author Shaoyu Liu
 * @date 2022-12-05
 */
@Component
@RocketMQMessageListener(topic = "test-topic-1", consumerGroup = "my-consumer_test-topic-1")
public class Consumer implements RocketMQListener<Message> {

    private static final Logger log = LoggerFactory.getLogger(Consumer.class);

    /**
     * 需要注意的是，onMessage（）封装了ACK机制，消费者往外抛异常时，RocketMQ认为消费失败，重新发送该条消息，否则默认消费成功
     */
    @Override
    public void onMessage(Message message) {
        log.info("received message:topic={},body={}" + message.getTopic() + new String(message.getBody()));
//        if(消费成功){
//
//        }else if(消费失败){
//            throw new Exception();
//        }

    }

}
