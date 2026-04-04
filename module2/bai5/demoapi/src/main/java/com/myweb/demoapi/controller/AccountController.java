package com.myweb.demoapi.controller;

import com.myweb.demoapi.dto.Account;
import com.myweb.demoapi.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/* Bai 6 */
@RestController
public class AccountController {

    @Autowired
    public AccountService accountService;

    // Postman -> body -> raw (JSON)
    // Request
//    {
//        "email": "12asdsadasdasdas12asdsadasdasdasd312asdsadasdasdasd312asdsadasdasdasd312asdsadasdasdasd312asdsadasdasdasd312asdsadasdasdasd312asdsadasdasdasd312asdsadasdasdasd312asdsadasdasdasd3d3@gmail.com",
//            "username": "aasd",
//            "password": "123456",
//            "age": 19
//    }
    @PostMapping("/api/form/fill")
    public ResponseEntity<List> fillTheForm(
            //annotation @Valid để Spring Boot kiểm tra xác thực dữ liệu của đối tượng này
            @Valid @RequestBody Account accountParams,
            //thông số để nhận kết quả xác thực dữ liệu
            BindingResult bindingResult
    ){
        if (bindingResult.hasErrors()) {
            //nếu 1 trong các dữ liệu không hợp lệ, lấy lỗi cụ thể của từng giá trị
            List<String> errors = new ArrayList<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.add(error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        Boolean isValidEmail = accountService.validEmailFormat(accountParams.getEmail());
        if (!isValidEmail) {
            List<String> errors = new ArrayList<>();
            errors.add("Email is not valid");
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }


}



