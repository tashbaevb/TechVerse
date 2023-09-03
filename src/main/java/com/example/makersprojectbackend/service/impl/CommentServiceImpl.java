package com.example.makersprojectbackend.service.impl;

import com.example.makersprojectbackend.entity.Comment;
import com.example.makersprojectbackend.entity.User;
import com.example.makersprojectbackend.entity.course.VideoLecture;
import com.example.makersprojectbackend.repository.CommentRepository;
import com.example.makersprojectbackend.repository.UserRepository;
import com.example.makersprojectbackend.repository.course.VideoLectureRepository;
import com.example.makersprojectbackend.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final VideoLectureRepository videoLectureRepository;

    @Override
    public Comment create(Comment comment, Long videoId, Long userId) {
        VideoLecture videoLecture = videoLectureRepository.findById(videoId)
                .orElseThrow(() -> new IllegalArgumentException("Курс не найден: " + videoId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден: " + userId));

        comment.setVideoLecture(videoLecture);
        comment.setUser(user);

        Comment createdComment = commentRepository.save(comment);

        return createdComment;
    }


    @Override
    public Comment createReply(Comment comment, Long videoId, Long userId, Long parentCommentId) {
        VideoLecture videoLecture = videoLectureRepository.findById(videoId)
                .orElseThrow(() -> new IllegalArgumentException("Курс не найден: " + videoId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден: " + userId));

        comment.setVideoLecture(videoLecture);
        comment.setUser(user);

        if (parentCommentId != null) {
            Comment parentComment = getById(parentCommentId);
            comment.setParentComment(parentComment);
        }

        Comment createdComment = commentRepository.save(comment);

        return createdComment;
    }


    @Override
    public List<Comment> getRepliesForComment(Long commentId) {
        Comment parentComment = getById(commentId);
        return commentRepository.findByParentComment(parentComment);
    }


    @Override
    public Comment getById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Комментарий не найден: " + id));
    }


    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }


    @Override
    public List<Comment> getCommentsByUserId(Long userId) {
        return commentRepository.findByUserId(userId);
    }


    @Override
    public List<Comment> getCommentsByVideoLectureId(Long videoId) {
        return commentRepository.findByVideoLectureId(videoId);
    }


    @Override
    public Comment update(Comment comment) {
        return commentRepository.save(comment);
    }


    @Override
    public void delete(Long id) {
        Comment comment = getById(id);
        commentRepository.delete(comment);
    }
}
