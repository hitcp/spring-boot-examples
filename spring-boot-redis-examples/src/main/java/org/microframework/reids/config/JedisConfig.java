//package org.microframework.reids.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cache.CacheManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisClusterConfiguration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.RedisSentinelConfiguration;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//import redis.clients.jedis.JedisPoolConfig;
//
//import java.io.Serializable;
//
///**
// * Redis Jedis 客户端配置
// * <p>
// * Jedis客户端支持模式：
// * 1.单机模式 <li>{@link RedisStandaloneConfiguration}</li>
// * 2.哨兵模式 <li>{@link RedisSentinelConfiguration}</li>
// * 3.集群模式 <li>{@link RedisClusterConfiguration}</li>
// *
// * @author Shaoyu Liu
// * @date 2022-08-26
// */
//@Configuration
//public class JedisConfig {
//
//    @Value("${spring.redis.host}")
//    public String host;
//
//    @Value("${spring.redis.port}")
//    public int port;
//
//    @Value("${spring.redis.password}")
//    public String password;
//
//    /**
//     * 选择缓存客户端：jedis、lettuce
//     *
//     * @return
//     */
////    @Bean
////    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
////        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.RedisCacheManagerBuilder
////                .fromConnectionFactory(redisConnectionFactory);
////        return builder.build();
////    }
//
//
//    @Bean
//    public RedisConnectionFactory redisConnectionFactory(JedisPoolConfig jedisPool,
//                                                         RedisStandaloneConfiguration jedisConfig) {
//        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(jedisConfig);
//        connectionFactory.setPoolConfig(jedisPool);
//        return connectionFactory;
//    }
//
//    /**
//     * Jedis 连接工厂，选择连接redis服务器的模式
//     *
//     * @param redisStandaloneConfiguration
//     * @return
//     */
//    @Bean
//    public JedisConnectionFactory jedisConnectionFactory(RedisStandaloneConfiguration redisStandaloneConfiguration) {
//        return new JedisConnectionFactory(redisStandaloneConfiguration);
//    }
//
//    /**
//     * 1.单机模式
//     *
//     * @return
//     */
//    @Bean
//    public RedisStandaloneConfiguration redisStandaloneConfiguration() {
//        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
//        redisStandaloneConfiguration.setHostName(host);
//        redisStandaloneConfiguration.setPort(port);
//        redisStandaloneConfiguration.setPassword(password);
//        return redisStandaloneConfiguration;
//    }
//
////
////    /**
////     * 2.哨兵模式
////     *
////     * @return
////     */
////    @Bean
////    public RedisSentinelConfiguration redisSentinelConfiguration() {
////        RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration();
////        // TODO 定义配置
////        return redisSentinelConfiguration;
////    }
////
////    /**
////     * 3.集群模式
////     *
////     * @return
////     */
////    @Bean
////    public RedisClusterConfiguration redisClusterConfiguration() {
////        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
////        // TODO 定义配置
////        return redisClusterConfiguration;
////    }
//
//    /**
//     * RedisTemplate 序列化配置
//     * 默认序列化方式 JdkSerializationRedisSerializer
//     * 存储形式：不可读、字节数组
//     * 使用场景：复杂类型使用
//     * 缺点：不可读取可读形式数据（解释：只能查到字节数组形式的数据，如果数据是可读形式则读取不到数据，显示null，此时可使用StringRedisTemplate尝试获取）
//     *
//     * @param jedisConnectionFactory
//     * @return
//     */
//    @Bean
//    public RedisTemplate<String, Serializable> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
//        RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
//        redisTemplate.setConnectionFactory(jedisConnectionFactory);
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
//     * @param jedisConnectionFactory
//     * @return
//     */
//    @Bean
//    public StringRedisTemplate stringRedisTemplate(JedisConnectionFactory jedisConnectionFactory) {
//        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
//        // TOOD 父类已经设置，子类不必重复设置
////        stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
////        stringRedisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
////        stringRedisTemplate.setHashKeySerializer(new StringRedisSerializer());
////        stringRedisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
//        stringRedisTemplate.setConnectionFactory(jedisConnectionFactory);
//        return stringRedisTemplate;
//    }
//}
