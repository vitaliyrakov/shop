package com.example.shop.service;

import com.example.shop.model.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();

    Customer findById(int id);

}