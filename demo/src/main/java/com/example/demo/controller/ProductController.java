package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.mercado.Repository.CategoryRepository;
import com.example.mercado.Repository.ProductRepository;
import com.example.mercado.model.Category;
import com.example.mercado.model.Product;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductController(ProductRepository productRepository,
            CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/category/{category_id}")
    public List<Product> getProductsByCategory(@PathVariable Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category != null) {
            return productRepository.findByCategory(category);
        }
        return new ArrayList<>();
    }

    @GetMapping("/below-price/{maxPrice}")
    public List<Product> getProductsBelowMaxPrice(@PathVariable double maxPrice) {
        return productRepository.findByProducsBelowMaxPrice(maxPrice);
    }
}