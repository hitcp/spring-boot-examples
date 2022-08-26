package org.microframework.reids.lettuce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author Shaoyu Liu
 * @date 2022-08-26
 */
public class LettuceClient {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


}
