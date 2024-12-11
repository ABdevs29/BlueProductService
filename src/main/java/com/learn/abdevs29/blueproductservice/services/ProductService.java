package com.learn.abdevs29.blueproductservice.services;

import com.learn.abdevs29.blueproductservice.exceptions.ProductNotFoundException;
import com.learn.abdevs29.blueproductservice.models.Category;
import com.learn.abdevs29.blueproductservice.models.Product;

import java.util.List;

public interface ProductService {
    public Product getProductById(long id);
    public List<Product> getAllProducts();
    public List<Product> getProductsByCategory(String category);
    public List<Category> getAllCategories();
    public Product updateProductById(long id, Product product) throws ProductNotFoundException;
}
