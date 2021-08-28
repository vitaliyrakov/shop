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
//@RolesAllowed("ADMIN")
public class ProductController {

    private final ProductService productService;

    @GetMapping()
//    @RolesAllowed({"ADMIN", "USER"})
    public String showProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        return "showProducts";
    }

    @GetMapping("/{id}")
//    @RolesAllowed({"ADMIN", "USER"})
    public String showProduct(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "showProduct";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "editProduct";
    }

    @GetMapping("/new")
    public String create(@ModelAttribute("product") Product product) {
        return "editProduct";
    }

    @PostMapping()
    public String save(@ModelAttribute("product") Product product) {
        productService.save(product);
        return "redirect:/products";
    }

    @PostMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        productService.delete(id);
        return "redirect:/products";
    }
}
