package com.myweb.basic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {

    @GetMapping("/demo/hello")
    public ResponseEntity<String> displayHelloMe(@RequestParam String name, @RequestParam Integer age) {
        String aString = "Hello " + name + ", " + age + " years old";
        return new ResponseEntity<>(aString, HttpStatus.OK);
    }
}
