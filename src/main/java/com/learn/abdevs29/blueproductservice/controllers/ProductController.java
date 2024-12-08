package com.learn.abdevs29.blueproductservice.controllers;

import com.learn.abdevs29.blueproductservice.models.Product;
import com.learn.abdevs29.blueproductservice.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
