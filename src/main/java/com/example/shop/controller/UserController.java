package com.example.shop.controller;

import com.example.shop.model.entity.User;
import com.example.shop.model.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
//@RolesAllowed("ADMIN")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping()
    public String showUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "showUsers";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "showUser";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "editUser";
    }

    @GetMapping("/new")
    public String create(@ModelAttribute("user") User user) {
        return "editUser";
    }

    @PostMapping()
    public String save(@ModelAttribute("user") User user) {
        user.setRegDate(new Date());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return "redirect:/users";
    }

    @PostMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/users";
    }

}