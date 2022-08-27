//package org.microframework.reids.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.core.StringRedisTemplate;
//
///**
// * @author Shaoyu Liu
// * @date 2022-08-27
// */
//@Configuration
//public class RedissonConfig {
//    @Bean
//    public StringRedisTemplate stringRedisTemplate(RedissonConnectionFactory lettuceConnFactory) {
//        StringRedisTemplate template = new StringRedisTemplate();
//        template.setConnectionFactory(lettuceConnFactory);
//        RedisUtils.template(template);
//        return template;
//    }
//}
