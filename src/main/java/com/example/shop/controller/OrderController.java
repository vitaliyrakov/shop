package com.example.shop.controller;

import com.example.shop.model.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
@RolesAllowed({"ADMIN", "USER"})
public class OrderController {

    private final OrderService orderService;

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
        orderService.createOrder();
        return "redirect:/orders";
    }

}