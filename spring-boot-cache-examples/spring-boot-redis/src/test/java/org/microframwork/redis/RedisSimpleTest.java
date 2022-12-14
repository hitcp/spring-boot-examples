package org.microframwork.redis;

import org.junit.jupiter.api.Test;
import org.microframework.reids.RedisApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest(classes = RedisApplication.class)
public class RedisSimpleTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testInsert() {
        redisTemplate.opsForValue().set("spring:redis", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        Object o = redisTemplate.opsForValue().get("spring:redis");
        System.out.println(o);
    }
}
