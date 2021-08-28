package com.example.shop.model.service;

import com.example.shop.model.entity.Cart;

import java.util.List;

public interface CartService {

    List<Cart> findAll();

    Cart findById(int id);

    void save(Cart cart);

    Cart getCart();

}
