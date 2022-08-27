package org.microframework.reids.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.connection.ConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Shaoyu Liu
 * @date 2022-08-27
 */
@Configuration
public class RedissonConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.password}")
    private String password;

//    @Bean
//    public StringRedisTemplate stringRedisTemplate(RedissonConnectionFactory redissonConnectionFactory) {
//        StringRedisTemplate template = new StringRedisTemplate();
//        template.setConnectionFactory(redissonConnectionFactory);
//        RedisUtils.template(template);
//        return template;
//    }

    @Bean
    public RedissonClient getRedisson() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://" + host + ":" + port).setPassword(password);
        //添加主从配置
//        config.useMasterSlaveServers().setMasterAddress("").setPassword("").addSlaveAddress(new String[]{"",""});
//        config.useClusterServers();
//        config.useSentinelServers();
//        config.useReplicatedServers();
//        config.useCustomServers(new ConnectionManager());
        return Redisson.create(config);
    }

}
