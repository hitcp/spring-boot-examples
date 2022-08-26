package org.microframework.reids.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author Shaoyu Liu
 * @date 2022-08-26
 */
public class RedisTemplateTest {
    @Autowired
    private RedisTemplate redisTemplate;
}
