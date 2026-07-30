package com.redis.service;

import com.redis.entity.Product;
import com.redis.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product){
        return this.productRepository.save(product);
    }

    public List<Product> getAllProducts(){
        return this.productRepository.findAll();
    }

    public Product getProductById(Long id){
        return this.productRepository.findById(id).orElse(null);
    }

    public Product updateProduct(Long id, Product updatedProduct){
        Product existingProduct = this.productRepository.findById(id).orElse(null);

        if(existingProduct == null){
            return null;
        }

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setCategory(updatedProduct.getCategory());
        existingProduct.setPrice(updatedProduct.getPrice());

        return this.productRepository.save(existingProduct);
    }

    public boolean deleteProduct(Long id){
        if(!this.productRepository.existsById(id)){
            return false;
        }

        this.productRepository.deleteById(id);
        return true;
    }

}
