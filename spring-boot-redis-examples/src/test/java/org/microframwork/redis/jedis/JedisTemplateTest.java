package org.microframwork.redis.jedis;

import org.junit.Test;
import org.microframwork.redis.BaseTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Shaoyu Liu
 * @date 2022-08-26
 */

public class JedisTemplateTest extends BaseTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testInsert() {
        stringRedisTemplate.opsForValue().set("spring:redis", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        String s = stringRedisTemplate.opsForValue().get("spring:redis");
        System.out.println(s);
    }

}
