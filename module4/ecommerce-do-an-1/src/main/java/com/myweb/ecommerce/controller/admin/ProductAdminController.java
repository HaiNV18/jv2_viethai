package com.myweb.ecommerce.controller.admin;

import com.myweb.ecommerce.enums.ProductStatus;
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

@Controller
@RequestMapping("/admin")
public class ProductAdminController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/product/recycle", produces = MediaType.TEXT_HTML_VALUE)
    public String showListProductRecycle(
            @RequestParam(defaultValue = "0") int page
            , Model model
    ) {
        int pageSize = 1; // item per page
        Page<Product> listProduct = productService.getProductsByStatus(PageRequest.of(page, pageSize), ProductStatus.DELETED.getCode());

        int totalPages = listProduct.getTotalPages();
        int currentPage = page;	//chỉ số của trang hiện tại
        int maxPagesToShow = 5;	//hiển thị tối đa 5 links của các trang trước và sau trang hiện tại
        int startPage = Math.max(0, currentPage - maxPagesToShow / 2);
        int endPage = Math.min(totalPages - 1, startPage + maxPagesToShow - 1);
        if ((endPage - startPage) < (maxPagesToShow - 1)) {
            startPage = Math.max(0, endPage - (maxPagesToShow - 1));
        }

        model.addAttribute("listProduct", listProduct);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "admin/list-product-recycle";
    }

    @GetMapping(value = "/product/update-status/{idProduct}")
    public String changeProductStatus(@PathVariable Integer idProduct) {
        Product product = productService.getProductByProductId(idProduct);
        product.setStatus(ProductStatus.INACTIVE);
        productService.updateProduct(idProduct, product);
        return "redirect:/admin/product/recycle";
    }
}
