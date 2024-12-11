package com.learn.abdevs29.blueproductservice.controllers;

import com.learn.abdevs29.blueproductservice.exceptions.ProductNotFoundException;
import com.learn.abdevs29.blueproductservice.models.Category;
import com.learn.abdevs29.blueproductservice.models.Product;
import com.learn.abdevs29.blueproductservice.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getProductById (@PathVariable("id") long id) {
        return productService.getProductById(id);
    }

    @GetMapping("")
    public List<Product> getAllProducts () {
        return productService.getAllProducts();
    }

    @GetMapping("/category/{name}")
    public List<Product> getProductsByCategory (@PathVariable("name") String name) {
        return productService.getProductsByCategory(name);
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories () {
        return productService.getAllCategories();
    }

    @PutMapping("/{id}")
    public Product replaceProductById (@PathVariable("id") long id, @RequestBody Product product) throws ProductNotFoundException {
        return productService.updateProductById(id, product);
    }
}
