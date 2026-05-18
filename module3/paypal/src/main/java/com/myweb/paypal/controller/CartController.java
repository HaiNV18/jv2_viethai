package com.myweb.paypal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {

    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("productName", "Áo thun Premium");
        model.addAttribute("price", 15.99);
        model.addAttribute("qty", 2);
        model.addAttribute("total", 31.98);
        return "cart";
    }
}
