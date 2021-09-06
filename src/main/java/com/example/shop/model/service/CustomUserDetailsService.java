package com.example.shop.model.service;

import com.example.shop.model.entity.User;
import com.example.shop.model.repository.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> myUser = userRepository.findOneByLogin(userName);
        if (myUser == null) {
            throw new UsernameNotFoundException("Unknown user: " + userName);
        } else {
            return new UserDetailsImpl(myUser.get());
        }
    }
}
