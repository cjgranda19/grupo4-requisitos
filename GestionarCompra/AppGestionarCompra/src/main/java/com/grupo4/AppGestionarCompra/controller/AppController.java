package com.grupo4.AppGestionarCompra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String showCartPage(Model model) {
        model.addAttribute("message_cart", "");
        return "cart";
    }

    @PostMapping("/cart")
    public String pay(Model model) {
        model.addAttribute("message_cart", "Pedido exitoso");
        return "cart";
    }

    @GetMapping("/payment")
    public String showPaymentPage(Model model) {
        model.addAttribute("message_payment", "");
        return "payment";
    }

    @PostMapping("/payment")
    public String processPayment(Model model) {
        // Simulacion de error en el pago
        boolean paymentError = Math.random() < 0.5; // 50% de probabilidad de error

        if (paymentError) {
            model.addAttribute("message_payment", "Error en el pago");
            return "payment";
        }
        else {
            model.addAttribute("message_cart", "Pedido exitoso");
            return "redirect:/cart";
        }
    }
}
