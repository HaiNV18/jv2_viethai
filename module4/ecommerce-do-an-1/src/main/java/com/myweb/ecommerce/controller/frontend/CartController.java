package com.myweb.ecommerce.controller.frontend;

import com.myweb.ecommerce.dto.EmailDto;
import com.myweb.ecommerce.dto.request.PlaceOrderRequest;
import com.myweb.ecommerce.service.BillService;
import com.myweb.ecommerce.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CartController {

    @Autowired
    public BillService billService;

    @Autowired
    public EmailService emailService;

    @GetMapping("/cart")
    public String cart() {
        return "frontend/cart";
    }

    @GetMapping("/checkout")
    public String checkout() {
        return "frontend/checkout";
    }

    @PostMapping("/checkout-place-order")
    public String checkoutPlaceOrder(PlaceOrderRequest req) throws MessagingException {
        System.out.println(req.getCartData());
        billService.save(req);

        // Send email
        EmailDto emailDto = new EmailDto();
        emailDto.setEmailTo(req.getEmail());
        emailDto.setSubject("Order Success");
        emailDto.setBody("Đặt hàng thành công!");
        emailDto.setCustomerName("Hai");


        emailService.sendEmailOrderSuccess(emailDto);

        return "frontend/checkout";
    }
}

