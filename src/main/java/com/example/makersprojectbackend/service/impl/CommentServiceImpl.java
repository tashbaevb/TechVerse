package com.example.makersprojectbackend.service.impl;

import com.example.makersprojectbackend.dto.CommentDto;
import com.example.makersprojectbackend.entity.Comment;
import com.example.makersprojectbackend.entity.User;
import com.example.makersprojectbackend.entity.course.Course;
import com.example.makersprojectbackend.mappers.CommentMapper;
import com.example.makersprojectbackend.repository.CommentRepository;
import com.example.makersprojectbackend.repository.UserRepository;
import com.example.makersprojectbackend.repository.course.CourseRepository;
import com.example.makersprojectbackend.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;
    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public Comment create(Comment comment, Long courseId, Long userId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Курс не найден: " + courseId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден: " + userId));

        comment.setCourse(course);
        comment.setUser(user);
        comment.setTimestamp(LocalDateTime.now());

        Comment createdComment = commentRepository.save(comment);

        CommentDto commentDto = commentMapper.convertToDTO(createdComment);
        messagingTemplate.convertAndSend("/text/comments", commentDto);

        return createdComment;
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
    public List<Comment> getCommentsByUserId(Long userId){
        return commentRepository.findByUserId(userId);
    }


    @Override
    public List<Comment> getCommentsByCourseId(Long courseId){
        return commentRepository.findByCourseId(courseId);
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
