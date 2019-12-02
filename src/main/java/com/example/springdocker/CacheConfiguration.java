package com.example.springdocker;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@EnableCaching
@Configuration
public class CacheConfiguration {

    @Bean
    public JedisConnectionFactory redisConnectionFactory(){
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration("redis",6379);
        return new JedisConnectionFactory(configuration);
    }

    @Bean
    public CacheManager redisCacheManager(){
        return RedisCacheManager.create(redisConnectionFactory());
    }
}
