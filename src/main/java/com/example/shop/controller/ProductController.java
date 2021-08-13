package com.example.shop.controller;

import com.example.shop.model.entity.Product;
import com.example.shop.model.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    public String showProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        return "showProducts";
    }

    @GetMapping("/{id}")
    public String showProduct(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "showProduct";
    }

    @GetMapping("/new")
    public String addProduct(@ModelAttribute("product") Product product) {
        return "newProduct";
    }

    @PostMapping()
    @RolesAllowed({"ADMIN"})
    public String create(@ModelAttribute("product") Product product) {
        productService.save(product);
        return "redirect:/products";
    }

    @PostMapping("/{id}")
    @RolesAllowed({"ADMIN"})
    public String delete(@PathVariable("id") int id) {
        productService.delete(id);
        return "redirect:/products";
    }
}
