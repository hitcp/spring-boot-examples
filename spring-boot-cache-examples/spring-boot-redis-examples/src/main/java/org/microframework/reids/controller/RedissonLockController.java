package org.microframework.reids.controller;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Shaoyu Liu
 * @date 2022-08-27
 */
@RequestMapping("/redisson")
public class RedissonLockController {

    @Autowired
    private RedissonClient redissonClient;

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
        RLock rLock = redissonClient.getLock(LOCK_KEY_PREFIX);
        rLock.lock();
        try {
            int stock = (Integer) redisTemplate.opsForValue().get("stock");
            if (stock > 0) {
                TICKET_PRODUCT = stock - 1;
                redisTemplate.opsForValue().set("stock", TICKET_PRODUCT);
                System.out.println("卖出1件商品，库存剩余：" + TICKET_PRODUCT);
            } else {
                System.out.println("售卖失败！库存不足");
            }

        } finally {
            rLock.unlock();
        }
    }
}
