package com.example.shop.controller;

import com.example.shop.model.entity.Order;
import com.example.shop.model.entity.Product;
import com.example.shop.model.service.CartService;
import com.example.shop.model.service.OrderService;
import com.example.shop.model.service.ProductService;
import com.example.shop.model.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
@RolesAllowed({"ADMIN", "USER"})
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final CartService cartService;
    private final ProductService productService;

    @GetMapping()
    public String showOrders(Model model) {
        // TODO: 28.08.2021 нужно выводить лишь заказы текущего пользователя 
        model.addAttribute("orders", orderService.findAll());
        return "showOrders";
    }

    @GetMapping("/{id}")
    public String showOrder(@PathVariable("id") int id, Model model) {
        model.addAttribute("order", orderService.findById(id));
        return "showOrder";
    }

    @PostMapping()
    public String createOrder() {
        List<Integer> productIds = cartService.getCart().getProducts().stream()
                .map(Product::getId)
                .collect(Collectors.toList());

        List<Product> products = productIds.stream()
                .map(id -> productService.findById(id))
                .collect(Collectors.toList());

        orderService.save(Order.builder()
                .products(products)
                .date(new Date())
                .user(userService.getCurrentUser())
                .build());

        cartService.clearCart();

        return "redirect:/orders";
    }

}