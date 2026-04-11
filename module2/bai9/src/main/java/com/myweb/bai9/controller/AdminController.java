package com.myweb.bai9.controller;

import com.myweb.bai9.dto.CategoryDto;
import com.myweb.bai9.dto.ProductDto;
import com.myweb.bai9.service.CategoryService;
import com.myweb.bai9.service.ExternalApiService;
import com.myweb.bai9.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    public CategoryService categoryService;

    @Autowired
    public ExternalApiService externalApiService;

    @Autowired
    public ProductService productService;

    @GetMapping("/admin/dashboard")
    public String showDashboard(Model model) throws IOException, InterruptedException {
        return "admin/index";
    }

    @GetMapping("/admin/list-product")
    public String showListProduct(
            @RequestParam(defaultValue = "1") int page,
            Model model
    ) throws IOException, InterruptedException {
        int limit = 12; // limit items in per page
        int skip = (page - 1) * limit; // skip item 0-8

        String url = "https://dummyjson.com/products?limit=" + limit + "&skip=" + skip;

        JsonNode data = externalApiService.fetchDataFromExternalApi(url);

        List<ProductDto> listProduct = productService.getlistProductByJsonNode(data);

        // Calculate pages
        int total = data.get("total").asInt();
        int totalPages = (int) Math.ceil((double) total / limit); // Lam tron

        model.addAttribute("listProduct", listProduct);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "admin/list-product";
    }

    @GetMapping("/admin/create-product")
    public String showCreateProduct(Model model) throws IOException, InterruptedException {
        String urlCatList = "https://dummyjson.com/products/category-list";
        JsonNode dataCatList = externalApiService.fetchDataFromExternalApi(urlCatList);
        List<CategoryDto> listCat = categoryService.getlistCatByJsonNode(dataCatList);
        model.addAttribute("listCat", listCat);
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

    @GetMapping("/admin/update-product/{id}")
    public String showUpdateProduct(@PathVariable Integer id, Model model) throws IOException, InterruptedException {
        String urlCatList = "https://dummyjson.com/products/category-list";
        JsonNode dataCatList = externalApiService.fetchDataFromExternalApi(urlCatList);
        List<CategoryDto> listCat = categoryService.getlistCatByJsonNode(dataCatList);

        String url = "https://dummyjson.com/products/" + id;
        JsonNode data = externalApiService.fetchDataFromExternalApi(url);
        ProductDto product = productService.getProductByJsonNode(data);
        model.addAttribute("product", product);
        model.addAttribute("listCat", listCat);
        return "admin/update-product";
    }

    @PostMapping("/product/update-product")
    public String processAddData(@RequestParam Integer id,
                                 @RequestParam String title,
                                 @RequestParam String description,
                                 @RequestParam String price,
                                 @RequestParam String category) {

        productService.updateProduct(
                id,
                title,
                description,
                price,
                category
        );

        return "redirect:/admin/list-product";
    }

}
