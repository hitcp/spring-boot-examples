package org.microframwork.redis.letuuce;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.microframework.reids.RedisApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Shaoyu Liu
 * @date 2022-08-26
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisApplication.class)
public class StringRedisTemplateTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testInsert() {
        stringRedisTemplate.opsForValue().set("spring:redis", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        String s = stringRedisTemplate.opsForValue().get("spring:redis");
        System.out.println(s);
    }

}
