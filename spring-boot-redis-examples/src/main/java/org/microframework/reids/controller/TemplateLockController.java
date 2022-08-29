package org.microframework.reids.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.TimeUnit;

/**
 * @author Shaoyu Liu
 * @date 2022-08-29
 */
@RequestMapping("/template")
public class TemplateLockController {
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 商品总量
     */
    public static int TICKET_PRODUCT = 100;

    /**
     * 锁超时时间（秒）
     */
    public static long LOCK_TIMEOUT = 30;

    /**
     * 分布式锁前缀
     */
    public static String LOCK_KEY_PREFIX = "lock_product";

    @GetMapping("createOrder")
    public void createOrder() {
        try {
            redisTemplate.opsForValue().set("order", "100", 30, TimeUnit.SECONDS);
            if (!redisTemplate.hasKey("order")) {
                // TODO
            }
        } finally {
            redisTemplate.delete("order");
        }
    }

}
