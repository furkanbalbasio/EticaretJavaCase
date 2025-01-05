package com.balbasio.EticaretJavaCase.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/redis")
public class RedisController {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisController(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostMapping("/set")
    public ResponseEntity<String> setRedisKeyValue(
            @RequestParam String key,
            @RequestParam String value) {
        redisTemplate.opsForValue().set(key, value);
        return ResponseEntity.ok("Key-Value added to Redis: " + key + " -> " + value);
    }

    @GetMapping("/get")
    public ResponseEntity<String> getRedisValue(@RequestParam String key) {
        Object value = redisTemplate.opsForValue().get(key);
        if (value != null) {
            return ResponseEntity.ok("Value for key '" + key + "': " + value.toString());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Key '" + key + "' not found in Redis");
        }
    }
}