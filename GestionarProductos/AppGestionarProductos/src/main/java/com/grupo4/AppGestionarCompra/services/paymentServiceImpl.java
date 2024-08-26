package com.grupo4.AppGestionarCompra.services;

import org.springframework.stereotype.Service;

@Service
public class paymentServiceImpl implements paymentService {
    @Override
    public boolean processPayment(String cardNumber) {
        if (cardNumber.equals("123456789")) {
            return true;
        } else {
            return false;
        }
    }

}
