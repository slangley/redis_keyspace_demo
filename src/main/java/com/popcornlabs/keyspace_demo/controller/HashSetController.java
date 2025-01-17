package com.popcornlabs.keyspace_demo.controller;

import com.popcornlabs.keyspace_demo.model.HashSetData;
import com.popcornlabs.keyspace_demo.service.RedisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hashset")
public class HashSetController {
    private final RedisService redisService;
    private static final long DEFAULT_TTL = 60;

    public HashSetController(RedisService redisService) {
        this.redisService = redisService;
    }

    @PostMapping("/{hashsetId}")
    public ResponseEntity<Void> createHashSet(
            @PathVariable String hashsetId,
            @RequestBody HashSetData data,
            @RequestParam(defaultValue = "60") long ttl) {
        redisService.createHashSet(hashsetId, data, ttl);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{hashsetId}")
    public ResponseEntity<Void> deleteHashSet(@PathVariable String hashsetId) {
        redisService.deleteHashSet(hashsetId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{hashsetId}/ttl/{seconds}")
    public ResponseEntity<Void> updateTTL(
            @PathVariable String hashsetId,
            @PathVariable long seconds) {
        redisService.updateTTL(hashsetId, seconds);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{hashsetId}")
    public ResponseEntity<HashSetData> getHashSet(@PathVariable String hashsetId) {
        return ResponseEntity.ok(redisService.getHashSet(hashsetId));
    }
} 