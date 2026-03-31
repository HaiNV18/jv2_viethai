package com.myweb.basic.controller;

import com.myweb.basic.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @GetMapping(value="/product_detail")
    public String productDetailContext(Model model) {
        String img = "https://placehold.co/400x300/286CB5/FFFFFF?text=Product+Image";

        List<Product> listProduct = new ArrayList<>();
        Product product1 = new Product();
        product1.setId(1);
        product1.setName("OPPO");
        product1.setDescription("mo ta product 1");
        product1.setPrice(10000000);
        listProduct.add(product1);

        Product product2 = new Product();
        product2.setId(2);
        product2.setName("Samsung");
        product2.setDescription("mo ta product 2");
//        product2.setPrice(20000000);
        listProduct.add(product2);

        model.addAttribute("listProduct", listProduct);
        model.addAttribute("productImage", img);
        return "product_detail";
    }


//    @GetMapping(value="/demo/product_detail")
//    public String productDetail(Model model) {
//        String img = "https://placehold.co/400x300/286CB5/FFFFFF?text=Product+Image";
//        model.addAttribute("productName", "IPhone");
//        model.addAttribute("productImage", img);
//        return "product_detail";
//    }
}
