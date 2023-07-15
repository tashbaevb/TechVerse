package com.example.makersprojectbackend.service;

import com.example.makersprojectbackend.entity.Comment;

import java.util.List;

public interface CommentService {

    Comment create(Comment comment);

    Comment getById(Long id);

    List<Comment> getAll();

    Comment update(Comment commentDetails);

    void delete(Long id);


}
