package com.example.makersprojectbackend.service;

import com.example.makersprojectbackend.entity.Comment;

import java.util.List;

public interface CommentService {

    Comment create(Comment comment, Long courseId, Long userId);

    Comment createReply(Comment comment, Long courseId, Long parentCommentId, Long userId);

    List<Comment> getRepliesForComment(Long commentId);

    Comment getById(Long id);

    List<Comment> getAll();

    List<Comment> getCommentsByUserId(Long userId);

    List<Comment> getCommentsByCourseId(Long courseId);

    Comment update(Comment comment);

    void delete(Long id);
//
//    Comment addToFavorites(Long commentId, Long userId);
//
//    Comment removeFromFavorites(Long commentId, Long userId);
//
//    int getFavoriteUserCount(Long commentId);
//
//    List<UserDto> getFavoriteUsers(Long commentId);
//
//    List<CommentDto> getFavoriteCommentsByUser(Long userId);
}
