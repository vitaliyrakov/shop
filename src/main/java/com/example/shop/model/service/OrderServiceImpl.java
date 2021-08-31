package com.example.shop.model.service;

import com.example.shop.model.entity.Order;
import com.example.shop.model.entity.Product;
import com.example.shop.model.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final CartService cartService;
    private final ProductService productService;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    @Transactional
    public Order findById(int id) {
        return orderRepository.getById(id);
    }

    @Override
    @Transactional
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    @Transactional
    public void delete(int id) {
        orderRepository.deleteById(id);
    }

    @Override
    public void createOrder() {
        List<Integer> productIds = cartService.getCart().getProducts().stream()
                .map(Product::getId)
                .collect(Collectors.toList());

        List<Product> products = productIds.stream()
                .map(id -> productService.findById(id))
                .collect(Collectors.toList());

        save(Order.builder()
                .products(products)
                .date(new Date())
                .user(userService.getCurrentUser())
                .build());

        cartService.clearCart();
    }

}
