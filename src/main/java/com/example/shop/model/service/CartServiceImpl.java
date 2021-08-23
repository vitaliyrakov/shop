package com.example.shop.model.service;

import com.example.shop.model.entity.Cart;
import com.example.shop.model.entity.Product;
import com.example.shop.model.entity.User;
import com.example.shop.model.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserService userService;

    @Override
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
//    @Transactional(readOnly = true)
    public Cart findById(int id) {
//        return cartRepository.findById(id).stream().peek(it -> Hibernate.initialize(it.getProducts())).findFirst().orElse(null);
        return cartRepository.getById(id);
    }

    @Override
    public Cart getCart() {
        return cartRepository.getById(getCartId());
    }

    @Override
    public void save(Cart cart) {
        cartRepository.save(cart);
    }
//
//    @Override
//    public void delete(int id) {
//        productRepository.deleteById(id);
//    }

    private int getCartId() {

//        User currentUser = userService.getCurrentUser();
//
//        Cart cart = currentUser.getCart();
//
//        if (cart == null) {
////            cart = cartRepository.save(
////                    Cart.builder()
////                            .id(currentUser.getId())
////                            .build());
//        }
//        return cart.getId();
        return 1;
    }

}
