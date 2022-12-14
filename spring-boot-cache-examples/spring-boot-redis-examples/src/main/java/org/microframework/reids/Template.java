package org.microframework.reids;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author Shaoyu Liu
 * @date 2022-08-26
 */
public class Template {

    /**
     * RedisTemplate 默认使用 JdkSerializationRedisSerializer，存入数据会将数据先序列化成字节数组然后在存入Redis数据库。
     * 存储形式：不可读、字节数组
     * 使用场景：复杂类型使用
     * 缺点：只能查到字节数组形式的数据，如果数据是可读形式则读取不到数据，显示null，此时可使用StringRedisTemplate尝试获取
     */
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * StringRedisTemplate 默认使用 StringRedisSerializer
     * 存储形式：可读形式、字符串
     * 使用场景：字符串
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisOperations<String, Object> redisOperations;

//    Spring 三大Template:RedisTemplate，RestTemplate，JdbcTemplate

    /**
     * @return
     */
    @Bean
    public RedisOperations redisOperations() {
        return new StringRedisTemplate();
//        return new RedisTemplate<>();
    }


}
