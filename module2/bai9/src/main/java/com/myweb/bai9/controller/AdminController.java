package com.myweb.bai9.controller;

import com.myweb.bai9.dto.ProductDto;
import com.myweb.bai9.service.ExternalApiService;
import com.myweb.bai9.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tools.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    public ExternalApiService externalApiService;

    @Autowired
    public ProductService productService;

    @GetMapping("/admin/dashboard")
    public String showDashboard(Model model) throws IOException, InterruptedException {
        return "admin/index";
    }

    @GetMapping("/admin/list-product")
    public String showListProduct(Model model) throws IOException, InterruptedException {
        return "admin/tables";
    }

    @GetMapping("/admin/create-product")
    public String showCreateProduct(Model model) throws IOException, InterruptedException {
        return "admin/create-product";
    }


    @PostMapping("/product/add-product")
    public String processAddData(@RequestParam String title,
                                 @RequestParam String description,
                                 @RequestParam String price,
                                 @RequestParam String category) {

        productService.addProduct(
                title,
                description,
                price,
                category
        );

        return "redirect:/admin/list-product";
    }
}
