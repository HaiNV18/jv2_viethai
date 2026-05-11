package com.myweb.mongo_anime.controller;

import com.myweb.mongo_anime.model.Movie;
import com.myweb.mongo_anime.service.CommentService;
import com.myweb.mongo_anime.service.MovieService;
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
public class MovieAdminController {

    @Autowired
    public MovieService movieService;

    @Autowired
    public CommentService commentService;

    @GetMapping(value = "/movie/list", produces = MediaType.TEXT_HTML_VALUE)
    public String showMovieList(@RequestParam(defaultValue = "0") int page, Model model) {
        int pageSize = 3;	//số lượng movie hiển thị trong 1 trang
        Page<Movie> moviePage = movieService.findAllPagination(PageRequest.of(page, pageSize));
        int totalPages = moviePage.getTotalPages();
        int currentPage = page;	//chỉ số của trang hiện tại
        int maxPagesToShow = 5;	//hiển thị tối đa 5 links của các trang trước và sau trang hiện tại
        int startPage = Math.max(0, currentPage - maxPagesToShow / 2);
        int endPage = Math.min(totalPages - 1, startPage + maxPagesToShow - 1);
        if ((endPage - startPage) < (maxPagesToShow - 1)) {
            startPage = Math.max(0, endPage - (maxPagesToShow - 1));
        }
        model.addAttribute("list", moviePage.getContent());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "admin/list-movie";	//đường dẫn tới file HTML trong templates
    }

    @GetMapping("/movie/create")
    public String showCreate(Model model) {
        return "admin/insert-movie";
    }
}
