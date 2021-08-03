package com.example.shop.model.service;

import com.example.shop.model.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(int id);

    void save(User user);

    void delete(int id);
}
