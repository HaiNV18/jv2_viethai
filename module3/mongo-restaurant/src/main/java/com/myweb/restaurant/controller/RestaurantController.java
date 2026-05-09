package com.myweb.restaurant.controller;

import com.myweb.restaurant.model.Restaurant;
import com.myweb.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    public RestaurantService restaurantService;

    @GetMapping("/upload")
    public String showUploadPage(Model model) {
        return "unit4";
    }

    @GetMapping(value = "/list", produces = MediaType.TEXT_HTML_VALUE)
    public String showListPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String keyword, // không bắt buộc có keyword
            @RequestParam(required = false) String borough,
            Model model
    ) {//mặc định là trang đầu tiên
        int pageSize = 10;	//mỗi trang hiển thị tối đa 10 dữ liệu
        
        Page<Restaurant> restaurantPage =
                restaurantService.searchRestaurant(
                        keyword,
                        borough,
                        PageRequest.of(page, pageSize)
                );

        int totalPages = restaurantPage.getTotalPages();
        int currentPage = page;
        int maxPagesToShow = 5;	//hiển thị tối đa 5 chỉ số trang
        int startPage = Math.max(0, currentPage - maxPagesToShow / 2);
        int endPage = Math.min(totalPages - 1, startPage + maxPagesToShow - 1);
        if ((endPage - startPage) < (maxPagesToShow - 1)) {	//điều chỉnh lại số trang hiển thị
            startPage = Math.max(0, endPage - (maxPagesToShow - 1));
        }
        model.addAttribute("list", restaurantPage.getContent());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "admin/list-restaurants";
    }

    @GetMapping("/detail/{restaurantId}")
    public String showRestaurantDetail(@PathVariable String restaurantId, Model model) {
        Restaurant restaurant = restaurantService.findById(restaurantId);
        model.addAttribute("restaurant", restaurant);
        return "admin/update-restaurant";
    }

    @PostMapping("/update/{id}")
    public String updateRestaurant(@PathVariable("id") String restaurantId,
                                   @ModelAttribute("restaurant") Restaurant restaurant) {
        Restaurant oldObject = restaurantService.findById(restaurantId);
        restaurantService.updateRestaurant(oldObject, restaurant);
        return "redirect:/restaurant/list";
    }

    @PutMapping("/api/restaurants/change-id")
    public ResponseEntity<String> updateRestaurantId(
            @RequestParam String oldId,
            @RequestParam String newId) {
        try {
            restaurantService.updateRestaurantIdWithSave(oldId, newId);
            return ResponseEntity.ok("Restaurant ID updated in both collections");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }



}
