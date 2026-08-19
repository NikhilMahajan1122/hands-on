package com.redis.service;

import com.redis.entity.Product;
import com.redis.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SpringCacheProductService {

    private final ProductRepository productRepository;

    public SpringCacheProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        return this.productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    @Cacheable(value = "products", key = "#id")
    public Product getProductById(Long id) {
        log.info("Fetching product from MySQL for ID: {}", id);
        return this.productRepository.findById(id).orElse(null);
    }

    @CachePut(value = "products", key = "#id")
    public Product updateProduct(Long id, Product updatedProduct) {

        Product existingProduct = this.productRepository.findById(id).orElse(null);

        if (existingProduct == null) {
            return null;
        }

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setCategory(updatedProduct.getCategory());
        existingProduct.setPrice(updatedProduct.getPrice());

        Product savedProduct = this.productRepository.save(existingProduct);

        log.info("Product {} updated in MySQL and cache updated", id);

        return savedProduct;
    }

    @CacheEvict(value = "products", key = "#id")
    public boolean deleteProduct(Long id) {

        if (!this.productRepository.existsById(id)) {
            return false;
        }

        this.productRepository.deleteById(id);

        log.info("Product {} deleted from MySQL and cache evicted", id);

        return true;
    }
}