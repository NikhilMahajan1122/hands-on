package com.redis.controller;

import com.redis.entity.Product;
import com.redis.service.ProductHashService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("redis/hash/products")
public class ProductHashController {

    private final ProductHashService productHashService;

    public ProductHashController(ProductHashService productHashService){
        this.productHashService = productHashService;
    }

    @PostMapping
    public ResponseEntity<String> saveProduct(@RequestBody Product product){
        productHashService.saveProduct(product);

        return new ResponseEntity<>("Product saved successfully", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){
        Product product = productHashService.getProduct(id);
        if(product == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        boolean deleted = productHashService.deleteProduct(id);
        if(!deleted){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
