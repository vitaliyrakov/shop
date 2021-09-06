package com.example.shop.model.service;

import com.example.shop.model.entity.Cart;

public interface CartService {

    void save(Cart cart);

    Cart getCart();

    void clearCart();

    void addProduct(int Id);

    void delProduct(int Id);

}
