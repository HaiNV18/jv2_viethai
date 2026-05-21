package com.myweb.mongo_anime.controller;

import com.myweb.mongo_anime.dto.ChartDTO;
import com.myweb.mongo_anime.dto.MovieDTO;
import com.myweb.mongo_anime.model.Movie;
import com.myweb.mongo_anime.service.GenreService;
import com.myweb.mongo_anime.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class DashboardController {

    @Autowired
    public MovieService movieService;

    @Autowired
    public GenreService genreService;

    @GetMapping(value = "/dashboard", produces = MediaType.TEXT_HTML_VALUE)
    public String showDashboard(Model model) {

        // loc theo ngon ngu
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

        Long movieEng = 0L;
        Long movieJapan = 0L;
        Long movieKorea = 0L;
        Long movieFrance = 0L;
        if (filtered.get("EN") != null) {
            movieEng = filtered.get("EN");
        }
        if (filtered.get("JP") != null) {
            movieJapan = filtered.get("JP");
        }
        if (filtered.get("KR") != null) {
            movieKorea = filtered.get("KR");
        }
        if (filtered.get("FR") != null) {
            movieFrance = filtered.get("FR");
        }

        // Get movie by genres
        List<ChartDTO> listMovieGenre = movieService.getMoviesByGenre();

        Map<String, Integer> listMoviesByGenre = new LinkedHashMap<>();
        for (ChartDTO item : listMovieGenre) {
            listMoviesByGenre.put(item.getLabel(), item.getValue());
        }

        // top movies by vote
        List<Movie> listVoteAverage = movieService.findTop5ByOrderByVoteAverageDesc();

        List<MovieDTO> listTopVoteAverage = listVoteAverage.stream().map(item -> {
            MovieDTO dto =  new MovieDTO();
            dto.setTitle(item.getTitle());
            dto.setLanguage(item.getOriginalLanguage());
            dto.setVoteCount(item.getVoteCount());
            dto.setVoteAverage(item.getVoteAverage());

            List<String> listName = genreService.findNameByListId(item.getGenre());
            dto.setGenre(String.join(", ", listName));

            return dto;
        }).collect(Collectors.toList());

        // Recent movies
        List<Movie> listRecentMovies = movieService.findTop5ByOrderByReleaseDateDesc();

        // 10 movies most popular
        List<Movie> listMostPopular = movieService.findTop10ByOrderByVoteCountDesc();

        model.addAttribute("languageCounts", filtered);
        model.addAttribute("movieEng", movieEng);
        model.addAttribute("movieJapan", movieJapan);
        model.addAttribute("movieKorea", movieKorea);
        model.addAttribute("movieFrance", movieFrance);

        model.addAttribute("listMoviesByGenre", listMoviesByGenre);

        model.addAttribute("listTopVoteAverage", listTopVoteAverage);
        model.addAttribute("listRecentMovies", listRecentMovies);
        model.addAttribute("listMostPopular", listMostPopular);

        return "admin/index";
    }


}
