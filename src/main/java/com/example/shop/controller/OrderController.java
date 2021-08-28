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

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final CartService cartService;
    private final ProductService productService;

    @GetMapping()
    public String showOrders(Model model) {
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

        Order order = new Order();
        order.setProducts(products);
        order.setDate(new Date());
        order.setUser(userService.getCurrentUser());
        orderService.save(order);

        cartService.clearCart();

        return "redirect:/orders";
    }

}