package com.myweb.mongo_anime.controller;

import com.myweb.mongo_anime.model.Movie;
import com.myweb.mongo_anime.service.CommentService;
import com.myweb.mongo_anime.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class MovieAdminController {

    @Autowired
    public MovieService movieService;

    @Autowired
    public CommentService commentService;

    @GetMapping(value = "/movie/list", produces = MediaType.TEXT_HTML_VALUE)
    public String showMovieList(@RequestParam(defaultValue = "0") int page, Model model) {
        int pageSize = 10;	//số lượng movie hiển thị trong 1 trang
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

    @GetMapping("/movie/detail/{movieId}")
    public String showMovieDetail(@PathVariable String movieId, Model model) {
        Movie movie = movieService.findById(movieId);
        model.addAttribute("movie", movie);
        return "admin/update-movie";
    }

    @GetMapping("/movie/create")
    public String showCreate(Model model) {
        model.addAttribute("movie", new Movie());
        return "admin/insert-movie";
    }

    @PostMapping("/movie/insert")
    public String insertMovie(
            @Valid @ModelAttribute("movie") Movie req,
            BindingResult result,
            @RequestParam("thumbnailFile") MultipartFile file
    ) throws IOException {

        // validation model
        if (result.hasErrors()) {
            return "admin/insert-movie";
        }

        // validation file
        if (file.isEmpty()) {

            result.rejectValue(
                    "thumbnail",
                    "error.movie",
                    "Thumbnail is required"
            );
            return "admin/insert-movie";
        }

        String fileName = file.getOriginalFilename();
        String uploadDir = System.getProperty("user.dir")
                + "/src/main/resources/static/uploads/";
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        file.transferTo(new File(uploadDir + fileName));

        // Save file to database
        req.setThumbnail(fileName);
        req.setReleaseDate("2019-04-20");
        movieService.saveMovie(req);

        return "redirect:/admin/movie/list";
    }
}

