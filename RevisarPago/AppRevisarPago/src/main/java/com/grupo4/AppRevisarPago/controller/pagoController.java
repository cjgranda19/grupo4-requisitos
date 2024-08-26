package com.grupo4.AppRevisarPago.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class pagoController {

    private boolean pagoConfirmado = false;

    @GetMapping("/revisar_pago")
    public String revisarPago(Model model) {
        model.addAttribute("pagoConfirmado", pagoConfirmado);
        return "revisar_pago";
    }

    @PostMapping("/confirmar_pago")
    public String confirmarPago(Model model) {
        pagoConfirmado = true;
        model.addAttribute("pagoConfirmado", pagoConfirmado);
        return "revisar_pago";
    }
}
