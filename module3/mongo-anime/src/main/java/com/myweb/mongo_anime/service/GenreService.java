package com.myweb.mongo_anime.service;

import com.myweb.mongo_anime.model.Genre;
import com.myweb.mongo_anime.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreService {

    @Autowired
    public GenreRepository genreRepo;

    public Genre findById(String id) {
        return genreRepo.findById(id).orElse(null);
    }

    public List<Genre> findAll() {
        return genreRepo.findAll();
    }

    public List<String> findNameByListId(List<String> listId) {
        List<String> listNameGenre = new ArrayList<String>();
        if (listId != null && !listId.isEmpty()) {
            for (String id : listId) {
                Genre genre = findById(id);
                listNameGenre.add(genre.getName());
            }
        }
        return listNameGenre;
    }
}
