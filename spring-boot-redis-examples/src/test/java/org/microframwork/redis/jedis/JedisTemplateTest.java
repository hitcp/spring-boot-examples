package org.microframwork.redis.jedis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.microframework.reids.RedisApplication;
import org.microframework.reids.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
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
public class JedisTemplateTest {


    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public static final String REDIS_PREFIX = "spring:redis";

    @Test
    public void testInsert() {
        stringRedisTemplate.opsForValue().set("spring:redis", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        String s = stringRedisTemplate.opsForValue().get("spring:redis");
        System.out.println(s);
    }

    @Test
    public void testConnectionFactory() {
        System.out.println(redisTemplate.getConnectionFactory().getClass().getName());
    }

    @Test
    public void testPut() {
        User user = new User("刘邵宇", "Jerry");

        // 可以返回map映射，并且RedisConfig需要配置template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.opsForValue().set(REDIS_PREFIX + "liushaoyu", user);
        Object userByKey = redisTemplate.opsForValue().get(REDIS_PREFIX + "liushaoyu");
        System.out.println("1.*********************" + userByKey);

        // 只有hash类型可以强转为对象，并且RedisConfig需要配置template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.opsForHash().put(REDIS_PREFIX + "user:", "1", user);
        User userByHashKey = (User) redisTemplate.opsForHash().get(REDIS_PREFIX + "user:", "1");
        System.out.println("2.*********************" + userByHashKey);

        // 只可以转为object类型 什么也没做，只是对key value hashkey hashvalue做了默认序列化处理,stringRedisTemplate返回的是一个字符串无法转为map类型
        Object o = stringRedisTemplate.opsForHash().get(REDIS_PREFIX + "user:", "1");
        System.out.println("3.*********************" + o);
    }

}
