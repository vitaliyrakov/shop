package com.example.shop.controller;

import com.example.shop.model.entity.Cart;
import com.example.shop.model.entity.Order;
import com.example.shop.model.service.CartService;
import com.example.shop.model.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final ProductService productService;

    @GetMapping()
    public String showCart(Model model) {
        model.addAttribute("carts", cartService.findAll());
        return "showCart";
    }

    @GetMapping("/new")
    public String addOrder(@ModelAttribute("order") Order order) {
        return "newOrder";
    }

    @PostMapping("/{id}")
    public String add(@PathVariable("id") int id, Model model) {
//        model.addAttribute("product", productService.findById(id));
        Cart cart = new Cart();
        cart.setUser_id(id);
        cartService.save(cart);
        return "redirect:/cart";
//        return "showCart";
    }

}