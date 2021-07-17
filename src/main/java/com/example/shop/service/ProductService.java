package com.example.shop.service;

import com.example.shop.model.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findById(int id);

    void save(Product product);

    void delete(int id);
}
