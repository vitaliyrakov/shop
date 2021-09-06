package com.example.shop.model.repository;

import com.example.shop.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findOneByLogin(String login);

}

