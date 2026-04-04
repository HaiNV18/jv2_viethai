package com.myweb.demoapi.controller;

import com.myweb.demoapi.dto.Product;
import com.myweb.demoapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/demo")
public class DemoController {

    @Autowired
    public ProductService productSer;

    // http://localhost:8080/api/demo/products
    @GetMapping("/products") // GetMapping là phương thức get
    public ResponseEntity<List<String>> getAllProducts(){
        List<String> listProducts = new ArrayList<>();
        listProducts.add("Samsung");
        listProducts.add("iPhone");
        listProducts.add("OPPO");
        listProducts.add("Huawei");
        return new ResponseEntity<>(listProducts, HttpStatus.OK);
    }

    // http://localhost:8080/api/demo/product/detail
    @PostMapping("/product/detail")
    public ResponseEntity getProductDetail(@RequestParam Integer id) {
//        @RequestParam yêu cầu bắt buộc phải có tham số
        List<Product> listProducts = productSer.getListProduct();
        Product product = productSer.getProductById(listProducts, id);
        return new ResponseEntity<>(product, HttpStatus.OK);
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
        List<String> listProducts = new ArrayList<>();
        listProducts.add("Samsung"); // index=0 id=1
        listProducts.add("OPPO"); // index=1 id=2
        listProducts.add("iPhone"); // index=2 id=3
        listProducts.add("Huawei"); // index=3 id=4

        if (id <= listProducts.size()) {
            String getProduct = listProducts.get(id - 1);
            listProducts.remove(id - 1);
            System.out.println("Xóa sản phẩm có id là: " + id);
            System.out.println("Tên sản phẩm: " + getProduct);
            return new ResponseEntity<>(listProducts, HttpStatus.OK);
        }

        System.out.println("Không tìm thấy sản phẩm");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/test-header")
    public ResponseEntity getHeader(@RequestHeader Map<String, String> header) {
        System.out.println(header);
        System.out.println(header.get("Host"));
        System.out.println(header.get("ip"));

        // Split array, substring, concat để kiểm tra ip
        if (header.get("ip").equals("127.0.1.1")) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(header, HttpStatus.OK);
    }

}
