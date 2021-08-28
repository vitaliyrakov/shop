package com.example.shop.controller;

import com.example.shop.model.entity.Cart;
import com.example.shop.model.entity.Order;
import com.example.shop.model.entity.Product;
import com.example.shop.model.service.CartService;
import com.example.shop.model.service.OrderService;
import com.example.shop.model.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final CartService cartService;

    @GetMapping()
    public String showOrders(Model model) {
        model.addAttribute("orders", orderService.findAll());
        return "showOrders";
    }

    @GetMapping("/new/{id}")
    public String addOrder(@PathVariable("id") int id, Model model) {
//        Cart cart = cartService.getCart();
        Cart cart = cartService.findById(id);
        List<Product> products = cart.getProducts();
        Order order = new Order();
        order.setProducts(products);
        model.addAttribute("order", order);
        return "newOrder";
    }

    //    @PostMapping()
//    public String create(@ModelAttribute("order") Order order) {
//        order.setDate(new Date());
//        order.setUser(userService.findById(1));
//        orderService.save(order);
//        return "redirect:/orders";
//    }
    @PostMapping()
    public String create() {
        Cart cart = cartService.getCart();
        List<Product> products = cart.getProducts();
        Order order = new Order();
        order.setProducts(products);
        order.setDate(new Date());
        order.setUser(userService.findById(1));
        orderService.save(order);
        return "redirect:/orders";
    }

    @PostMapping("/del/{id}")
    public String delProduct(@PathVariable("id") int id) {
//        Cart cart = cartService.getCart();
//        List<Product> products = cart.getProducts();
//        products.remove(productService.findById(id));
//        cart.setProducts(products);
//        cartService.save(cart);
        return "redirect:/order";
    }

    @GetMapping("/{id}")
    public String showOrder(@PathVariable("id") int id, Model model) {
        model.addAttribute("order", orderService.findById(id));
        return "showOrder";
    }

//    @GetMapping("/{id}")
//    public String showOrder(@PathVariable("id") int id, Model model) {
//        model.addAttribute("order", orderService.findById(id));
//        return "showOrder";
//    }

}