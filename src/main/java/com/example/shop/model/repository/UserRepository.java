package com.example.shop.model.repository;

import com.example.shop.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

//    User findUserByUsername(String login);
//
//    User findUserByEmail(String email);
//
////    User findUserByPhoneNumber(String phoneNumber);
//
//    User findUserById(int id);

    Optional<User> findOneByLogin(String login);

}

