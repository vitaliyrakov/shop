package com.example.shop.model.service;

import com.example.shop.model.entity.Product;
import com.example.shop.model.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public Product findById(int id) {
        return productRepository.getById(id);
    }

    @Override
    @Transactional
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void delete(int id) {
        productRepository.deleteById(id);
    }

}
