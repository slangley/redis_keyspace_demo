package com.popcornlabs.keyspace_demo.listener;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyspaceEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisKeyspaceListener extends KeyspaceEventMessageListener {
    private static final Logger log = LoggerFactory.getLogger(RedisKeyspaceListener.class);
    private final RedisTemplate<String, String> redisTemplate;

    public RedisKeyspaceListener(RedisMessageListenerContainer listenerContainer, 
                               RedisTemplate<String, String> redisTemplate) {
        super(listenerContainer);
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected void doHandleMessage(Message message) {
        String channel = new String(message.getChannel());
        String body = new String(message.getBody());
        log.info("Received Redis keyspace notification - Channel: {}, Body: {}", channel, body);

    
    // Get all hash entries for this key
        Map<Object, Object> hashContents = redisTemplate.opsForHash().entries(body);
        
        log.info("Key operated on: {}", body);
        log.info("Hash contents for key {}: {}", body, hashContents);

    }
} 