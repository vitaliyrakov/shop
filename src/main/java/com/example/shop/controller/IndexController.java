package com.example.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class IndexController {

    @RequestMapping("/")
    public String home() {
        return "redirect:/products";
    }
}
