package com.lcl.galaxy.microservice.frontend.ticket.config;

import com.lcl.galaxy.microservice.frontend.ticket.entity.LocalCustomerStaff;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, LocalCustomerStaff> redisTemplate(RedisConnectionFactory connectionFactory){
        RedisTemplate<String, LocalCustomerStaff> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);

        // key 序列化
        redisTemplate.setKeySerializer(RedisSerializer.string());

        // value 序列化
        redisTemplate.setValueSerializer(RedisSerializer.json());

        return redisTemplate;
    }
}
