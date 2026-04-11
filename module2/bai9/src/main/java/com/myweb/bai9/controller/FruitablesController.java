package com.myweb.bai9.controller;

import com.myweb.bai9.dto.ProductDto;
import com.myweb.bai9.service.ExternalApiService;
import com.myweb.bai9.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import tools.jackson.databind.JsonNode;

import java.awt.print.Pageable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FruitablesController {

    @Autowired
    public ExternalApiService externalApiService;

    @Autowired
    public ProductService productService;

    private final SpringTemplateEngine templateEngine;

    public FruitablesController(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @GetMapping("/home")
    public String showHomepage(Model model) throws IOException, InterruptedException {
        JsonNode data = externalApiService.fetchDataFromExternalApi("https://dummyjson.com/products?limit=8");
        List<ProductDto> listProduct = productService.getlistProductByJsonNode(data);
        model.addAttribute("listProduct", listProduct);
        return "fruitables/index";
    }

    // /category/smartphones?page=1
    @GetMapping("/category/{cat}")
    public String showCategory(
            @PathVariable String cat,
            @RequestParam(defaultValue = "1") int page,
            Model model
    ) throws IOException, InterruptedException {
        int limit = 12; // limit items in per page
        int skip = (page - 1) * limit; // skip item 0-8

        String url = "https://dummyjson.com/products/category/" + cat + "?limit=" + limit + "&skip=" + skip;
        if (cat.equals("all")) {
            url = "https://dummyjson.com/products?limit=" + limit + "&skip=" + skip;
        }

        JsonNode data = externalApiService.fetchDataFromExternalApi(url);

        List<ProductDto> listProduct = productService.getlistProductByJsonNode(data);

        // Calculate pages
        int total = data.get("total").asInt();
        int totalPages = (int) Math.ceil((double) total / limit); // Lam tron

        model.addAttribute("listProduct", listProduct);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("category", cat);
        return "fruitables/shop";
    }

    @GetMapping(value = "/product-detail/{id}")
    public String showDetail(@PathVariable Integer id, Model model) throws IOException, InterruptedException {
        String url = "https://dummyjson.com/products/" + id;
        JsonNode data = externalApiService.fetchDataFromExternalApi(url);
        ProductDto product = productService.getProductByJsonNode(data);
        model.addAttribute("product", product);
        return "fruitables/shop-detail";
    }

    @GetMapping("/product-search")
    public String searchProduct(
            @RequestParam("k") String keyword,
            @RequestParam(defaultValue = "1") int page,
            Model model
    ) throws IOException, InterruptedException {

        int limit = 8;
        int skip = (page - 1) * limit;

        String url = "https://dummyjson.com/products/search?q="
                + keyword + "&limit=" + limit + "&skip=" + skip;

        JsonNode data = externalApiService.fetchDataFromExternalApi(url);

        List<ProductDto> listProduct = productService.getlistProductByJsonNode(data);

        int total = data.get("total").asInt();
        int totalPages = (int) Math.ceil((double) total / limit);

        model.addAttribute("listProduct", listProduct);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("keyword", keyword);

        return "fruitables/shop";
    }
    @GetMapping(value = "/contact", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String showContactpage() {
        Context context = new Context();
        return templateEngine.process("fruitables/contact", context);
    }

}
