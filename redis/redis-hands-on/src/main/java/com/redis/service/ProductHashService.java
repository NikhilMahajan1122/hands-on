package com.redis.service;

import com.redis.entity.Product;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductHashService {

    private static final String KEY_PREFIX = "product:";

    private final RedisTemplate<String, Object> redisTemplate;

    public ProductHashService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveProduct(Product product) {
        String key = KEY_PREFIX + product.getId();
        redisTemplate.opsForHash().put(key, "id", product.getId());
        redisTemplate.opsForHash().put(key, "name", product.getName());
        redisTemplate.opsForHash().put(key, "price", product.getPrice());
        redisTemplate.opsForHash().put(key, "category", product.getCategory());
    }

    public Product getProduct(Long id){
        String key = KEY_PREFIX + id;
        Object productId = redisTemplate.opsForHash().get(key, "id");
        if(productId == null){
           return null;
        }
        Product product = new Product();
        product.setId(((Number) productId).longValue());
        product.setName((String) redisTemplate.opsForHash().get(key, "name"));
        product.setPrice(((Number) redisTemplate.opsForHash().get(key, "price")).doubleValue());
        product.setCategory((String) redisTemplate.opsForHash().get(key, "category"));
        return product;
    }

    public boolean deleteProduct(Long id){
        return Boolean.TRUE.equals(redisTemplate.opsForHash().getOperations().delete(KEY_PREFIX + id));
    }
}