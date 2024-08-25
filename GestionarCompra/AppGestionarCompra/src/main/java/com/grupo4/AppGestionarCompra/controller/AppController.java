package com.grupo4.AppGestionarCompra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login() {
        return "redirect:/products";
    }

    @GetMapping("/products")
    public String showProductsPage() {
        return "products";
    }

    @GetMapping("/cart")
    public String showCartPage() {
        return "cart";
    }

    @PostMapping("/cart")
    public String pay() {
        return "redirect:/success";
    }

    @GetMapping("/payment")
    public String showPaymentPage() {
        return "payment";
    }

    @PostMapping("/payment")
    public String processPayment() {
        return "redirect:/success";
    }
}
