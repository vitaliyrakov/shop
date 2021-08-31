package com.example.shop.controller;

import com.example.shop.model.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping()
    public String showCart(Model model) {
        model.addAttribute("cart", cartService.getCart());
        return "showCart";
    }

    @PostMapping("/{id}")
    public String addProduct(@PathVariable("id") int id) {
        cartService.addProduct(id);
        return "redirect:/cart";
    }

    @PostMapping("/del/{id}")
    public String delProduct(@PathVariable("id") int id) {
        cartService.delProduct(id);
        return "redirect:/cart";
    }

}