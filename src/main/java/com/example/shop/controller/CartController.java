package com.example.shop.controller;

import com.example.shop.model.entity.Cart;
import com.example.shop.model.entity.Order;
import com.example.shop.model.entity.Product;
import com.example.shop.model.entity.User;
import com.example.shop.model.service.CartService;
import com.example.shop.model.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final ProductService productService;

    @GetMapping()
    public String showCart(Model model) {
        model.addAttribute("cart", cartService.getCart());
        return "showCart";
    }

    @GetMapping("/new")
    public String createOrder(@ModelAttribute("order") Order order) {
        return "newOrder";
    }

    @PostMapping("/{id}")
    public String addProduct(@PathVariable("id") int id) {
        Cart cart = cartService.getCart();
        List<Product> products = cart.getProducts();
        products.add(productService.findById(id));
        cart.setProducts(products);
        cartService.save(cart);
        return "redirect:/cart";
    }

    @PostMapping("/del/{id}")
    public String delProduct(@PathVariable("id") int id) {
        Cart cart = cartService.getCart();
        List<Product> products = cart.getProducts();
        products.remove(productService.findById(id));
        cart.setProducts(products);
        cartService.save(cart);
        return "redirect:/cart";
    }

}