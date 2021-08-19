package com.example.shop.model.service;

import com.example.shop.model.entity.Cart;
import com.example.shop.model.entity.Product;
import com.example.shop.model.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Override
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

//    @Override
//    @Transactional(readOnly = true)
//    public Product findById(int id) {
//        return productRepository.findById(id).stream().peek(it -> Hibernate.initialize(it.getCustomers())).findFirst().orElse(null);
//    }
//
    @Override
    public void save(Cart cart) {
        cartRepository.save(cart);
    }
//
//    @Override
//    public void delete(int id) {
//        productRepository.deleteById(id);
//    }

}
