package com.learn.abdevs29.blueproductservice.services;

import com.learn.abdevs29.blueproductservice.dtos.FakeStoreProductDto;
import com.learn.abdevs29.blueproductservice.exceptions.ProductNotFoundException;
import com.learn.abdevs29.blueproductservice.models.Category;
import com.learn.abdevs29.blueproductservice.models.Product;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
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

    @Override
    public List<Product> getProductsByCategory(String category) {
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject("https://fakestoreapi.com/products/category/" + category, FakeStoreProductDto[].class);
        List<Product> products = new ArrayList<>();

        for (FakeStoreProductDto fakeStoreProductDto: fakeStoreProductDtos) {
            products.add(convertFakeStoreProductToProduct(fakeStoreProductDto));
        }

        return products;
    }

    @Override
    public List<Category> getAllCategories() {
        String[] response = restTemplate.getForObject("https://fakestoreapi.com/products/categories", String[].class);

        List<Category> categories = new ArrayList<>();

        for (String category: response) {
            Category c = new Category();
            c.setName(category);
            categories.add(c);
        }
        return  categories;
    }

    @Override
    public Product updateProductById(long id, Product product) throws ProductNotFoundException {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(convertProductToFakeStoreProduct(product), FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto response = restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);

        if(response == null){
            throw new ProductNotFoundException("Product not found");
        }

        return convertFakeStoreProductToProduct(response);
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

    public FakeStoreProductDto convertProductToFakeStoreProduct (Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getName());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setCategory(product.getCategory().getName());
        fakeStoreProductDto.setPrice(product.getPrice());

        return fakeStoreProductDto;
    }
}
