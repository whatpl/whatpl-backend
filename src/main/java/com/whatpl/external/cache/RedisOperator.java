package com.whatpl.external.cache;

import com.whatpl.global.exception.BizException;
import com.whatpl.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class RedisOperator implements CacheOperator {

    private final RedisTemplate<String, Object> redisTemplate;

    public void put(String key, Object value, long expirationTime) {
        redisTemplate.opsForValue().set(
                key,
                value.toString(),
                expirationTime,
                TimeUnit.MILLISECONDS);
    }

    public String get(String key) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(key))
                .orElseThrow(() -> new BizException(ErrorCode.NOT_FOUND_DATA))
                .toString();
    }

    public boolean exists(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    public void delete(String key) {
            redisTemplate.delete(key);
        }
}