package com.ms.im.poc.backend.devops_microservice_product.controller;

import com.ms.im.poc.backend.devops_microservice_product.model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    List<Product> products = List.of(
            new Product(1L, "Product A", 10.0),
            new Product(2L, "Product B", 20.0)
    );


    @GetMapping
    public List<Product> getAllProducts() {
        return products;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        products.add(product);
        return product;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        // write code to get product by id from the list
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

}

