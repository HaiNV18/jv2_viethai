package com.myweb.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    @GetMapping("/demo/unit1_1")
    public String unit1_1() {
        return "unit1_1";
    }

    @GetMapping("/demo/unit1_2")
    public String unit1_2(Model model) {
        int a = 3;
        int b = 4;

        model.addAttribute("a", a);
        model.addAttribute("b", b);
        model.addAttribute("sum", a + b);
        model.addAttribute("sub", a - b);
        return "unit1_2";
    }
}
