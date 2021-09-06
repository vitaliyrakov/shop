package com.example.shop.model.service;

import com.example.shop.model.entity.Cart;
import com.example.shop.model.entity.Product;
import com.example.shop.model.entity.User;
import com.example.shop.model.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserService userService;
    private final ProductService productService;

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

    @Override
    public void addProduct(int id) {
        Cart cart = getCart();
        List<Product> products = cart.getProducts();
        products.add(productService.findById(id));
        cart.setProducts(products);
        save(cart);
    }

    @Override
    public void delProduct(int id) {
        Cart cart = getCart();
        List<Product> products = cart.getProducts();
        products.remove(productService.findById(id));
        cart.setProducts(products);
        save(cart);
    }

    @Transactional
    public int getCartId() {
        User currentUser = userService.getCurrentUser();

        Cart cart = currentUser.getCart();

        if (cart == null) {
            cart = cartRepository.save(
                    Cart.builder()
                            .user(currentUser)
                            .id(currentUser.getId())
                            .build());

        }
        return cart.getId();
    }

}
