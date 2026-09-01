package com.redis.service;

import com.redis.entity.Product;
import com.redis.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final RedisTemplate<String, Object> redisTemplate;
    private final String CACHE_KEY_PREFIX = "product:";

    public ProductService(ProductRepository productRepository, RedisTemplate<String, Object> redisTemplate) {
        this.productRepository = productRepository;
        this.redisTemplate = redisTemplate;
    }

    public Product createProduct(Product product) {
        return this.productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public Product getProductById(Long id) {
        String key = CACHE_KEY_PREFIX + id;

        Object cachedProduct = redisTemplate.opsForValue().get(key);
        if (cachedProduct != null) {
            log.info("Returning cached product for ID: {}", id);
            return (Product) cachedProduct;
        }
        log.info("Product not found in cache for ID: {}. Fetching from database.", id);
        Product product = this.productRepository.findById(id).orElse(null);
        if (product != null) {
            this.redisTemplate.opsForValue().set(key, product);
            log.info("Caching product for ID: {}", id);
        }
        return product;
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        String key = CACHE_KEY_PREFIX + id;

        Product existingProduct = this.productRepository.findById(id).orElse(null);
        if (existingProduct == null) {
            return null;
        }
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setCategory(updatedProduct.getCategory());
        existingProduct.setPrice(updatedProduct.getPrice());

        Product savedProduct = this.productRepository.save(existingProduct);

        redisTemplate.opsForValue().set(key, savedProduct);

        log.info("Product {} updated in MySQL and Redis cache", id);

        return savedProduct;
    }

    public boolean deleteProduct(Long id) {
        String key = CACHE_KEY_PREFIX + id;
        if (!this.productRepository.existsById(id)) {
            return false;
        }
        this.productRepository.deleteById(id);
        redisTemplate.delete(key);
        log.info("Product {} deleted from MySQL and cache evicted from Redis", id);
        return true;
    }

}
