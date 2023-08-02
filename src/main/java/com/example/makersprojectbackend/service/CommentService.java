package com.example.makersprojectbackend.service;

import com.example.makersprojectbackend.entity.Comment;

import java.util.List;

public interface CommentService {

    Comment create(Comment comment, Long courseId, Long userId);

    Comment getById(Long id);

    List<Comment> getAll();

    List<Comment> getCommentsByUserId(Long userId);

    List<Comment> getCommentsByCourseId(Long articleId);

    Comment update(Comment comment);

    void delete(Long id);
}
