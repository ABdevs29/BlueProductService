package com.learn.abdevs29.blueproductservice.services;

import com.learn.abdevs29.blueproductservice.models.Product;

import java.util.List;

public interface ProductService {
    public Product getProductById(long id);
    public List<Product> getAllProducts();
}
