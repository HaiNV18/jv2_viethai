package com.myweb.ecommerce.controller.frontend;

import com.myweb.ecommerce.model.Product;
import com.myweb.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String showHome(Model model) {
        List<Product> listProduct = productService.getAllProducts(8);
        List<Product> listNewestProduct = productService.getNewestProducts(8);

        model.addAttribute("listProduct", listProduct);
        model.addAttribute("listNewestProduct", listNewestProduct);
        return "frontend/index";
    }
}
