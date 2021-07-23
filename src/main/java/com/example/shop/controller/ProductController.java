package com.example.shop.controller;

import com.example.shop.model.entity.Product;
import com.example.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    public String showProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        return "products/showAll";
    }

    @GetMapping("/{id}")
    public String showProduct(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "products/show";
    }

    @PostMapping()
    public String create(@ModelAttribute("product") Product product) {
        productService.save(product);
        return "products/showAll";
    }

    @PostMapping("/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        productService.delete(id);
        return "products/showAll";
    }
}
