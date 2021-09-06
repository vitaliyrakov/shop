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

    @GetMapping("/edit/{id}")
    @RolesAllowed({"ADMIN"})
    public String editProduct(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "editProduct";
    }

    @GetMapping("/new")
    @RolesAllowed({"ADMIN"})
    public String createProduct(@ModelAttribute("product") Product product) {
        return "editProduct";
    }

    @PostMapping()
    @RolesAllowed({"ADMIN"})
    public String saveProduct(@ModelAttribute("product") Product product) {
        productService.save(product);
        return "redirect:/products";
    }

    @PostMapping("/{id}")
    @RolesAllowed({"ADMIN"})
    public String deleteProduct(@PathVariable("id") int id) {
        productService.delete(id);
        return "redirect:/products";
    }

}
