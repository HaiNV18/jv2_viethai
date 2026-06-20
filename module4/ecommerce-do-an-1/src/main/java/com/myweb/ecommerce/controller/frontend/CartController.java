package com.myweb.ecommerce.controller.frontend;

import com.myweb.ecommerce.dto.request.PlaceOrderRequest;
import com.myweb.ecommerce.service.BillService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CartController {

    @Autowired
    public BillService billService;

    @GetMapping("/cart")
    public String cart() {
        return "frontend/cart";
    }

    @GetMapping("/checkout")
    public String checkout() {
        return "frontend/checkout";
    }

    @PostMapping("/checkout-place-order")
    public String checkoutPlaceOrder(PlaceOrderRequest req) {

        System.out.println(req.getCartData());

        billService.save(req);
        return "frontend/checkout";
    }
}

