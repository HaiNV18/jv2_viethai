package com.myweb.demoapi.controller;

import com.myweb.demoapi.dto.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class DemoController {

    // http://localhost:8080/products?id=4
    @GetMapping("/products") // GetMapping là phương thức get
    public ResponseEntity<List<String>> getAllProducts(@RequestParam Integer id){
//        @RequestParam yêu cầu bắt buộc phải có tham số
        List<String> listProducts = new ArrayList<>();
        listProducts.add("Samsung"); // index=0
        listProducts.add("OPPO"); // index=1
        listProducts.add("iPhone"); // index=2
        listProducts.add("Huawei"); // index=3

        if (id <= listProducts.size()) {
            String getProduct = listProducts.get(id - 1);
            List<String> products = new ArrayList<>();
            products.add(getProduct);
            return new ResponseEntity<>(products, HttpStatus.OK);
        }

        return new ResponseEntity<>(listProducts, HttpStatus.OK);
    }

    // http://localhost:8080/product/detail
    @PostMapping("/product/detail")
    public ResponseEntity getProductDetail(
            @RequestParam String name,
            @RequestParam Integer price,
            @RequestParam String color
    ) {
        System.out.println("Tên sản phẩm: " + name);
        System.out.println("Giá : " + price);
        System.out.println("Màu sắc: " + color);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/product/detail/body")
    public ResponseEntity<Product> createNewProductFromBodyParam(
            @RequestBody Product productDetail
    ) {
        return new ResponseEntity<>(productDetail, HttpStatus.OK);
    }

    @PutMapping("/product/update")
    public ResponseEntity updateProduct(
            @RequestParam String name,
            @RequestParam Integer price,
            @RequestParam String color
    ) {
        System.out.println("Tên sản phẩm: " + name);
        System.out.println("Giá : " + price);
        System.out.println("Màu sắc: " + color);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/product/detele")
    public ResponseEntity deleteProduct(
            @RequestParam Integer id
    ) {
        System.out.println("Xóa sản phẩm có id là: " + id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
