package com.myweb.mongo_anime.service;

import com.myweb.mongo_anime.model.Comment;
import com.myweb.mongo_anime.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    public CommentRepository commentRepo;

    public Comment createNewComment(Comment comment) {
        return commentRepo.save(comment);
    }
}
