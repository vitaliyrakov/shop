package com.example.shop.model.service;

import com.example.shop.model.entity.Cart;
import com.example.shop.model.entity.User;
import com.example.shop.model.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserService userService;

    @Override
    @Transactional
    public Cart getCart() {
        return cartRepository.getById(getCartId());
    }

    @Override
    @Transactional
    public void save(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public void clearCart() {
        cartRepository.deleteById(getCartId());
    }

    @Transactional
    private int getCartId() {
        User currentUser = userService.getCurrentUser();

        Cart cart = currentUser.getCart();

        if (cart == null) {
            // TODO: 28.08.2021 отладить эту ветку
            cart = cartRepository.save(
                    Cart.builder()
                            .user(currentUser)
                            .build());

        }
        return cart.getId();
    }

}
