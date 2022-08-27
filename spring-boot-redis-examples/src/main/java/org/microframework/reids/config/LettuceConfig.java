//package org.microframework.reids.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.*;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//import java.io.Serializable;
//
///**
// * Redis Lettuce 客户端配置
// * <p>
// * Jedis客户端支持模式：
// * 1.单机模式配置      {@link RedisStandaloneConfiguration}
// * 2.主从模式配置      {@link RedisStaticMasterReplicaConfiguration}
// * 3.Socket模式配置   {@link RedisSocketConfiguration}
// * 4.哨兵模式配置      {@link RedisSentinelConfiguration}
// * 5.集群模式配置      {@link RedisClusterConfiguration}
// *
// * @author Shaoyu Liu
// * @date 2022-08-26
// */
//@Configuration
//public class LettuceConfig {
//
//    @Value("${spring.redis.host}")
//    private String host;
//
//    @Value("${spring.redis.port}")
//    private int port;
//
//    @Value("${spring.redis.password}")
//    private String password;
//
//    /**
//     * 1.单机模式配置
//     *
//     * @return
//     */
//    @Bean
//    public LettuceConnectionFactory lettuceConnectionFactory() {
//        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
//        redisStandaloneConfiguration.setHostName(host);
//        redisStandaloneConfiguration.setPort(port);
//        redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
//        return new LettuceConnectionFactory(redisStandaloneConfiguration);
//    }
//
//    /**
//     * 2.主从模式配置
//     *
//     * @return
//     */
//    @Bean
//    public RedisStaticMasterReplicaConfiguration redisStaticMasterReplicaConfiguration() {
//        RedisStaticMasterReplicaConfiguration redisStaticMasterReplicaConfiguration = new RedisStaticMasterReplicaConfiguration(host, port);
//        // TODO 定义配置
//        return redisStaticMasterReplicaConfiguration;
//    }
//
//    /**
//     * 3.Socket模式配置
//     *
//     * @return
//     */
//    @Bean
//    public RedisSocketConfiguration redisSocketConfiguration() {
//        RedisSocketConfiguration redisSocketConfiguration = new RedisSocketConfiguration();
//        return redisSocketConfiguration;
//    }
//
//    /**
//     * 4.哨兵模式配置
//     *
//     * @return
//     */
//    @Bean
//    public RedisSentinelConfiguration redisSentinelConfiguration() {
//        RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration();
//        return redisSentinelConfiguration;
//    }
//
//    /**
//     * 5.集群模式配置
//     *
//     * @return
//     */
//    @Bean
//    public RedisClusterConfiguration redisClusterConfiguration() {
//        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
//        return redisClusterConfiguration;
//    }
//
//    /**
//     * RedisTemplate 序列化配置
//     * 默认序列化方式 JdkSerializationRedisSerializer
//     * 存储形式：不可读、字节数组
//     * 使用场景：复杂类型使用
//     * 缺点：不可读取可读形式数据（解释：只能查到字节数组形式的数据，如果数据是可读形式则读取不到数据，显示null，此时可使用StringRedisTemplate尝试获取）
//     *
//     * @param lettuceConnectionFactory
//     * @return
//     */
//    @Bean
//    public RedisTemplate<String, Serializable> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
//        RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
////        redisTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
//        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
//        return redisTemplate;
//    }
//
//    /**
//     * TODO 验证
//     * StringRedisTemplate 序列化配置 （StringRedisTemplate已经配置过序列化方式则StringRedisTemplate不用重复配置）
//     * 默认序列化方式 StringRedisSerializer
//     * 存储形式：可读形式、字符串
//     * 使用场景：字符串
//     * 缺点：不可读取字节数组
//     *
//     * @param lettuceConnectionFactory
//     * @return
//     */
//    @Bean
//    public StringRedisTemplate stringRedisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
//        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
//        // TOOD 父类已经设置，子类不必重复设置
////        stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
////        stringRedisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
////        stringRedisTemplate.setHashKeySerializer(new StringRedisSerializer());
////        stringRedisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
//        stringRedisTemplate.setConnectionFactory(lettuceConnectionFactory);
//        return stringRedisTemplate;
//    }
//}
