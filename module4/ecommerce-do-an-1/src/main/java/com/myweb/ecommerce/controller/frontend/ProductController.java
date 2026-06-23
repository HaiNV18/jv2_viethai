package com.myweb.ecommerce.controller.frontend;

import com.myweb.ecommerce.model.Product;
import com.myweb.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/shop/{brand}", produces = MediaType.TEXT_HTML_VALUE)
    public String showShop(@PathVariable String brand
            , @RequestParam(required = false) String keyword
            , Model model
    ) {
        List<Product> listProduct = new ArrayList<>();
        listProduct = productService.searchProduct(8, brand, keyword);

        model.addAttribute("brand", brand);
        model.addAttribute("keyword", keyword);
        model.addAttribute("listProduct", listProduct);

        return "frontend/shop";
    }

    @GetMapping(value = "/detail/{idProduct}", produces = MediaType.TEXT_HTML_VALUE)
    public String showProductDetail(@PathVariable Integer idProduct, Model model) {
        List<Product> listProduct = productService.getAllProducts(6);
        Product product = productService.getProductByProductId(idProduct);

        model.addAttribute("brand", product.getBrand());
        model.addAttribute("product", product);
        model.addAttribute("listProduct", listProduct);
        return "frontend/detail";
    }
}
