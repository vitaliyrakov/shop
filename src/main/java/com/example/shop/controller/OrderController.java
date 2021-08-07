package com.example.shop.controller;

import com.example.shop.model.entity.Order;
import com.example.shop.model.entity.User;
import com.example.shop.model.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Date;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping()
    @RolesAllowed({"ADMIN", "MANAGER"})
    public String showOrders(Model model) {
        model.addAttribute("orders", orderService.findAll());
        return "showOrders";
    }

    @GetMapping("/new")
    public String addOrder(@ModelAttribute("order") Order order) {
        return "newOrder";
    }

    @PostMapping()
    @RolesAllowed({"ADMIN", "MANAGER"})
    public String create(@ModelAttribute("order") Order order) {
        order.setDate(new Date());
//        order.setUser(new User());
        orderService.save(order);
        return "redirect:/orders";
    }

//    @GetMapping("/{id}")
//    public String showOrder(@PathVariable("id") int id, Model model) {
//        model.addAttribute("order", orderService.findById(id));
//        return "showOrder";
//    }

}