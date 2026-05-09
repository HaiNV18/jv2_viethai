package com.myweb.mongo_anime.controller;

import com.myweb.mongo_anime.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class DashboardController {

    @Autowired
    public MovieService movieService;

    @GetMapping(value = "/dashboard", produces = MediaType.TEXT_HTML_VALUE)
    public String showDashboard(Model model) {
        Map<String, Long> languageCounts = movieService.getAndGroupMoviesByLanguage();
        Map<String, Long> filtered = languageCounts.entrySet().stream()
                // .filter(e -> e.getValue() > 50)	//lọc những nhóm nào có số lượng movie lớn hơn 50
                .sorted(Map.Entry.<String, Long>comparingByValue()) //sắp xếp dữ liệu
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

        model.addAttribute("languageCounts", filtered);	//truyền dữ liệu qua trang web

        return "admin/index";
    }


}
