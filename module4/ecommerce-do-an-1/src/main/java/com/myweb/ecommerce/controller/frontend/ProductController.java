package com.myweb.ecommerce.controller.frontend;

import com.myweb.ecommerce.model.Product;
import com.myweb.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
            , @RequestParam(defaultValue = "0") int page
            , Model model
    ) {
        int pageSize = 1; // item per page
        Page<Product> listProduct = productService.searchProduct(PageRequest.of(page, pageSize), brand, keyword);

        int totalPages = listProduct.getTotalPages();
        int currentPage = page;	//chỉ số của trang hiện tại
        int maxPagesToShow = 5;	//hiển thị tối đa 5 links của các trang trước và sau trang hiện tại
        int startPage = Math.max(0, currentPage - maxPagesToShow / 2);
        int endPage = Math.min(totalPages - 1, startPage + maxPagesToShow - 1);
        if ((endPage - startPage) < (maxPagesToShow - 1)) {
            startPage = Math.max(0, endPage - (maxPagesToShow - 1));
        }

        model.addAttribute("brand", brand);
        model.addAttribute("keyword", keyword);
        model.addAttribute("listProduct", listProduct);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
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
