package com.myweb.basic.controller;

import com.myweb.basic.dto.ProductDto;
import com.myweb.basic.dto.ProductRequest;
import com.myweb.basic.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ApiController {

    @Autowired
    public ProductService productService;

    @GetMapping("/hello-world")
    public ResponseEntity helloWorld() {
        String message = "Hello World";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity getAllProducts(){
        List<String> productNames = new ArrayList<>();
        productNames.add("Apple");
        productNames.add("Samsung");
        productNames.add("OPPO");
        productNames.add("Xiaomi");
        return new ResponseEntity<>(productNames, HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity getDetailProducts(@PathVariable Integer id){
        List<ProductDto> listProducts = productService.getListProduct();

        for (ProductDto product : listProducts) {
            if (product.getId().equals(Long.valueOf(id))) {
                return ResponseEntity.ok(product);
            }
        }

        return new ResponseEntity<>(listProducts, HttpStatus.OK);
    }

    @PostMapping("/product/create")
    public ResponseEntity createProducts(@RequestBody ProductRequest req){
        ProductDto dto = new ProductDto();
        dto.setId(3L);
        dto.setName(req.getName());

        List<ProductDto> listProducts = productService.getListProduct();
        listProducts.add(dto);

        return new ResponseEntity<>(listProducts, HttpStatus.OK);
    }

    @PutMapping("/product/update")
    public ResponseEntity updateProducts(@RequestBody ProductRequest req){
        ProductDto dto = new ProductDto();
        List<ProductDto> listProducts = productService.getListProduct();

        listProducts.forEach(product -> {
            if (req.getId() == product.getId()) {
                dto.setId(req.getId());
                dto.setName(req.getName());
            }
        });

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/product/delete")
    public ResponseEntity updateProducts(@RequestParam Long id){
        List<ProductDto> listProducts = productService.getListProduct();

        List<ProductDto> result = listProducts.stream()
                .filter(product -> product.getId() != id)
                .toList();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
