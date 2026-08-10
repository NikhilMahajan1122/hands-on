package com.redis.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setValue(String key, Object value){
        redisTemplate.opsForValue().set(key, value);
    }

    public Object getValue(String key){
        return redisTemplate.opsForValue().get(key);
    }

    //For setting value with new key along with TTL
    public void setValueWithTTL(String key, Object value, Duration duration){
        redisTemplate.opsForValue().set(key, value, duration);
    }

    // For add/change expiration on an existing key
    public boolean setTTL(String key, Duration duration) {
        return redisTemplate.expire(key, duration);
    }

    //For getting TTL of an existing key
    public Long getTTL(String key){
        return redisTemplate.getExpire(key);
    }

    //For removing the TTL from existing key and making key permanent
    public boolean removeTTL(String key) {
        return redisTemplate.persist(key);
    }

    public boolean deleteValue(String key){
        return Boolean.TRUE.equals(redisTemplate.delete(key));
    }

}
