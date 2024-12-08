package com.learn.abdevs29.blueproductservice.services;

import com.learn.abdevs29.blueproductservice.dtos.FakeStoreProductDto;
import com.learn.abdevs29.blueproductservice.models.Category;
import com.learn.abdevs29.blueproductservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{

    private final RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(long id) {
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        if (fakeStoreProductDto != null) {
            return convertFakeStoreProductToProduct(fakeStoreProductDto);
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        List<Product> products = new ArrayList<>();

        for (FakeStoreProductDto fakeStoreProductDto: fakeStoreProductDtos) {
            products.add(convertFakeStoreProductToProduct(fakeStoreProductDto));
        }
        return products;
    }

    public Product convertFakeStoreProductToProduct (FakeStoreProductDto fakeStoreProductDto) {
        Product p = new Product();
        p.setName(fakeStoreProductDto.getTitle());
        p.setDescription(fakeStoreProductDto.getDescription());
        p.setPrice(fakeStoreProductDto.getPrice());
        Category c = new Category();
        c.setName(fakeStoreProductDto.getCategory());
        p.setCategory(c);

        return p;
    }
}
