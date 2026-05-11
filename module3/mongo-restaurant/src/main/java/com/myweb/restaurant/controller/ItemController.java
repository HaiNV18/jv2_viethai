package com.myweb.restaurant.controller;

import com.myweb.restaurant.model.Item;
import com.myweb.restaurant.model.Restaurant;
import com.myweb.restaurant.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    public ItemService itemService;

    @GetMapping(value = "/list", produces = MediaType.TEXT_HTML_VALUE)
    public String showListPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String name, // không bat buoc nhap name
            @RequestParam(required = false) Integer price,
            Model model
    ) {
        System.out.println(price);
        int pageSize = 10;	//mỗi trang hiển thị tối đa 10 dữ liệu
        Page<Item> itemPage =
                itemService.searchItem(
                        name,
                        PageRequest.of(page, pageSize)
                );

        int totalPages = itemPage.getTotalPages();
        int currentPage = page;
        int maxPagesToShow = 5;	//hiển thị tối đa 5 chỉ số trang
        int startPage = Math.max(0, currentPage - maxPagesToShow / 2);
        int endPage = Math.min(totalPages - 1, startPage + maxPagesToShow - 1);
        if ((endPage - startPage) < (maxPagesToShow - 1)) {	//điều chỉnh lại số trang hiển thị
            startPage = Math.max(0, endPage - (maxPagesToShow - 1));
        }
        model.addAttribute("list", itemPage.getContent());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "admin/list-items";
    }

    @GetMapping("/detail/{itemId}")
    public String showItemDetail(@PathVariable String itemId, Model model) {
        Item item = itemService.findByItemId(itemId);
        model.addAttribute("item", item);
        return "admin/update-item";
    }

    @PostMapping("/update/{itemId}")
    public String updateItem(@PathVariable("itemId") String itemId,
                                   @ModelAttribute("item") Item item) {
        Item oldObject = itemService.findByItemId(itemId);
        itemService.updateItem(oldObject, item);
        return "redirect:/item/list";
    }

    @GetMapping("/insert")
    public String insertItemView(Model model) {
        return "admin/insert-item";
    }

    @PostMapping("/insert")
    public String insertItem(@ModelAttribute("item") Item newItem) {
        itemService.save(newItem);
        return "redirect:/item/list";
    }
}
