package org.microframework.cache.spring.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * 通过 @EnableCaching 启用spring cache
 * @author Shaoyu Liu
 * @date 2022-08-24
 * @see org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration
 */
@EnableCaching
@Configuration
public class CacheConfig {
}
