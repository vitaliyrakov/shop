package com.example.shop.model.service;

import com.example.shop.model.entity.Order;

import java.util.List;

public interface OrderService {

    List<Order> findAll();

    Order findById(int id);

    void save(Order product);

    void delete(int id);

    void createOrder();
}
