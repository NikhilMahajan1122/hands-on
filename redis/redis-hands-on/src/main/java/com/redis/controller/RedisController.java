package com.redis.controller;

import com.redis.service.RedisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequestMapping("/redis")
public class RedisController {

    private final RedisService redisService;

    public RedisController(RedisService redisService) {
        this.redisService = redisService;
    }

    @PostMapping
    public ResponseEntity<String> setValue(@RequestParam String key, @RequestParam String value) {
        redisService.setValue(key, value);
        return ResponseEntity.ok("Value stored successfully");
    }

    @PostMapping("/set-with-ttl")
    public ResponseEntity<String> setValueWithTTL(@RequestParam String key, @RequestParam String value, @RequestParam long ttlSeconds) {
        redisService.setValueWithTTL(key, value, Duration.ofSeconds(ttlSeconds));
        return ResponseEntity.ok("Value stored successfully");
    }

    @PostMapping("/expire")
    public ResponseEntity<String> setTTL(@RequestParam String key, @RequestParam long ttlSeconds) {

        boolean updated = redisService.setTTL(key, Duration.ofSeconds(ttlSeconds));

        if (!updated) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("TTL set successfully.", HttpStatus.OK);
    }

    @GetMapping("/ttl")
    public ResponseEntity<Long> getTTL(@RequestParam String key) {
        Long ttl = redisService.getTTL(key);
        if (ttl == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ttl, HttpStatus.OK);
    }

    @PostMapping("/persist")
    public ResponseEntity<String> persist(@RequestParam String key) {

        boolean updated = redisService.removeTTL(key);

        if (!updated) {
            return new ResponseEntity<>("Key not found or no TTL set.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("TTL removed successfully.", HttpStatus.OK);
    }

    @GetMapping("/{key}")
    public ResponseEntity<Object> getValue(@PathVariable String key) {
        Object value = redisService.getValue(key);
        if (value == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(value, HttpStatus.OK);
    }

    @DeleteMapping("/{key}")
    public ResponseEntity<Void> deleteValue(@PathVariable String key) {
        boolean deleted = redisService.deleteValue(key);
        if (!deleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
