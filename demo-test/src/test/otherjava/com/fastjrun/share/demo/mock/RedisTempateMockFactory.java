/*
 * Copyright (C) 2019 Fastjrun, Inc. All Rights Reserved.
 */
package com.fastjrun.share.demo.mock;

import com.fastjrun.share.demo.entity.User;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.when;

@Configuration
public class RedisTempateMockFactory {
    @Bean("redisTemplate")
    public RedisTemplate<String, User> getRedisTemplate() {
        @SuppressWarnings("unChecked")
        RedisTemplate<String, User> redisTemplate =
          (RedisTemplate<String, User>) Mockito.mock(RedisTemplate.class);
        mockHasKeyMethdods(redisTemplate);
        mockOpsForValueMethdods(redisTemplate);
        return redisTemplate;
    }

    private void mockHasKeyMethdods(RedisTemplate<String, User> redisTemplate) {
        ArgumentMatcher<String> hasRedisKey = redisKey -> {
            boolean res = false;
            switch (redisKey.substring("myLogin_unitTest_".length())) {
                case "00000000000000000000000000000001":
                case "00000000000000000000000000001005":
                case "00000000000000000000000000001001":
                    res = true;
                    break;
                default:
            }
            return res;
        };
        when(redisTemplate.hasKey(argThat(hasRedisKey))).thenReturn(true);

    }


    private void mockOpsForValueMethdods(RedisTemplate<String, User> redisTemplate) {
        ValueOperations valueOperations = Mockito.mock(ValueOperations.class);
        mockValueOperationsMethdods(valueOperations);
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);

    }

    private void mockValueOperationsMethdods(ValueOperations valueOperations) {
        User user = new User();
        user.setSex(1);
        ArgumentMatcher<String> hasRedisKey = redisKey -> {
            boolean res = false;
            switch (redisKey.substring("myLogin_unitTest_".length())) {
                case "00000000000000000000000000001001":
                    user.setId(1001);
                    res = true;
                    break;
                case "00000000000000000000000000001005":
                    user.setId(1005);
                    res = true;
                    break;
                default:
            }
            return res;
        };
        when(valueOperations.get(argThat(hasRedisKey))).thenReturn(user);
    }
}
