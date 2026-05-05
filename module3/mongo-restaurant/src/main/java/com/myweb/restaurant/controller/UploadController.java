package com.myweb.restaurant.controller;

import com.myweb.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/file")
public class UploadController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/upload")
    public String handleUpload(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        String uploadResult = restaurantService.handleUpload(file);
        model.addAttribute(uploadResult);
        return uploadResult;
    }

    @GetMapping("/api/restaurants/{id}/with-items")
    public ResponseEntity<List<HashMap>> getRestaurantWithItems(@PathVariable("id") String restaurantId) {
        List<HashMap> info = restaurantService.getRestaurantWithItems(restaurantId);
        return new ResponseEntity<List<HashMap>>(info, HttpStatus.OK);
    }

    @GetMapping("/api/items/{id}/with-restaurants")
    public ResponseEntity<List<HashMap>> getItemWithRestaurant(@PathVariable("id") String restaurantId) {
        List<HashMap> info = restaurantService.getItemsWithRestaurantInfo(restaurantId);
        return new ResponseEntity<List<HashMap>>(info, HttpStatus.OK);
    }
}
