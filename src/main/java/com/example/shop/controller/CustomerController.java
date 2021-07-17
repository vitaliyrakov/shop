package com.example.shop.controller;

import com.example.shop.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping()
    public String showCustomers(Model model) {
        model.addAttribute("customers", customerService.findAll());
        return "customers/showAll";
    }

    @GetMapping("/{id}")
    public String showCustomer(@PathVariable("id") int id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "customers/show";
    }

}