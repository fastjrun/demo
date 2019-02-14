/*
 * Copyright (C) 2019 Fastjrun, Inc. All Rights Reserved.
 */
package com.fastjrun.share.demo.mock;

import static org.powermock.api.mockito.PowerMockito.when;

import org.powermock.api.mockito.PowerMockito;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import com.fastjrun.share.demo.entity.User;

@Configuration
public class UtilMockFactory {
    @Bean("redisTemplate")
    public RedisTemplate<String, User> getRedisTemplate() {
        RedisTemplate<String, User> redisTemplate = (RedisTemplate<String, User>) PowerMockito.mock(RedisTemplate.class);
        when(redisTemplate.hasKey("myLogin_unitTest_00000000000000000000000000000001")).thenReturn(true);
        ValueOperations valueOperations = PowerMockito.mock(ValueOperations.class);
        when(valueOperations.get("myLogin_unitTest_00000000000000000000000000000001")).thenReturn(new User());
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        return redisTemplate;
    }

    @Bean("rabbitTemplate")
    public RabbitTemplate getRabbitTemplate() {
        RabbitTemplate rabbitTemplate = PowerMockito.mock(RabbitTemplate.class);
        return rabbitTemplate;

    }
}
