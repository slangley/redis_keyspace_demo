package com.popcornlabs.keyspace_demo.service;

import com.popcornlabs.keyspace_demo.model.HashSetData;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    private final RedisTemplate<String, Object> redisTemplate;

    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void createHashSet(String hashsetId, HashSetData data, long ttlSeconds) {
        String key = "hashset:" + hashsetId;
        redisTemplate.opsForHash().put(key, "name", data.getName());
        redisTemplate.opsForHash().put(key, "phoneNumber", data.getPhoneNumber());
        redisTemplate.opsForHash().put(key, "email", data.getEmail());
        redisTemplate.expire(key, ttlSeconds, TimeUnit.SECONDS);
    }

    public void deleteHashSet(String hashsetId) {
        String key = "hashset:" + hashsetId;
        redisTemplate.delete(key);
    }

    public void updateTTL(String hashsetId, long seconds) {
        String key = "hashset:" + hashsetId;
        redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }

    public HashSetData getHashSet(String hashsetId) {
        String key = "hashset:" + hashsetId;
        HashSetData data = new HashSetData();
        data.setName((String) redisTemplate.opsForHash().get(key, "name"));
        data.setPhoneNumber((String) redisTemplate.opsForHash().get(key, "phoneNumber"));
        data.setEmail((String) redisTemplate.opsForHash().get(key, "email"));
        return data;
    }
} 