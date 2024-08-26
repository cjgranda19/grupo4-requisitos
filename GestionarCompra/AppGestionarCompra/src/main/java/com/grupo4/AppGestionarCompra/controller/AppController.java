package com.grupo4.AppGestionarCompra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.grupo4.AppGestionarCompra.services.paymentService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppController {

    @Autowired
    private paymentService paymentService;

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

    /*
    @GetMapping("/cart")
    public String showCartPage(Model model) {
        boolean productUnavailable = true; // Cambiar a true para simular un producto no disponible

        if (productUnavailable) {
            model.addAttribute("message_cart", "No hay disponibilidad");
            return "cart";
        }
        else {
            return "redirect:/payment";
        }
    }
    */

    @GetMapping("/cart")
    public String showCartPage(Model model) {
        boolean productUnavailable = true; // Simula un producto no disponible

        if (productUnavailable) {
            model.addAttribute("message_cart", "No hay disponibilidad");
        } else {
            model.addAttribute("message_cart", "");
        }
        return "cart";
        // Aquí podrías implementar lógica para verificar la disponibilidad real de los productos
        //model.addAttribute("message_cart", model.asMap().getOrDefault("message_cart", ""));
        //return "cart";
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
    public String processPayment(@RequestParam String cardNumber,  Model model) {
        // Simulacion de error en el pago
        //boolean paymentSuccess = (forceError == null || forceError) ? false : paymentService.processPayment();
        boolean paymentSuccess = paymentService.processPayment(cardNumber);

        if (paymentSuccess) {
            model.addAttribute("message_payment", "Pago exitoso");
            model.addAttribute("message_cart", "Pedido exitoso");
            //return "redirect:/cart";
            return "payment";
        } else {
            model.addAttribute("message_payment", "Error en el pago");
            return "payment";
        }
    }
}
