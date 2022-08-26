package org.microframework.reids.lettuce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import reactor.core.publisher.Mono;

import java.net.URL;

/**
 * @author Shaoyu Liu
 * @date 2022-08-26
 */
public class LettuceClient {
//    @Autowired
//    private ReactiveRedisTemplate<String, String> template;
//
//    public Mono<Long> addLink(String userId, URL url) {
//        return template.opsForList().leftPush(userId, url.toExternalForm());
//    }
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


}
