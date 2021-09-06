package com.example.shop.model.service;

import com.example.shop.model.entity.User;
import com.example.shop.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User findById(int id) {
        return userRepository.getById(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getCurrentUser() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username = principal instanceof UserDetails
                ? ((UserDetails) principal).getUsername()
                : principal.toString();

        return userRepository.findOneByLogin(username)
                .orElseThrow(NullPointerException::new);
    }

}
