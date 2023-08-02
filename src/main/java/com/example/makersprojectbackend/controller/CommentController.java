package com.example.makersprojectbackend.controller;

import com.example.makersprojectbackend.dto.CommentDto;
import com.example.makersprojectbackend.entity.Comment;
import com.example.makersprojectbackend.entity.User;
import com.example.makersprojectbackend.mappers.CommentMapper;
import com.example.makersprojectbackend.repository.UserRepository;
import com.example.makersprojectbackend.repository.course.CourseRepository;
import com.example.makersprojectbackend.service.impl.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentServiceImpl commentServiceImpl;
    private final CommentMapper commentMapper;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    @PostMapping("/create/{courseId}")
    public CommentDto createComment(@RequestBody CommentDto commentDto, @PathVariable Long courseId, Authentication authentication) {
        String email = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(email);
        User currentUser = optionalUser.orElseThrow(() -> new IllegalArgumentException("Пользователь не найден: " + email));

        Comment comment = commentMapper.convertToEntity(commentDto);
        comment.setCourse(courseRepository.findById(courseId).orElseThrow(() -> new IllegalArgumentException("Курс не найден: " + courseId)));
        comment.setUser(currentUser);

        return commentMapper.convertToDTO(commentServiceImpl.create(comment, courseId, currentUser.getId()));
    }


    @GetMapping("/get/{commentId}")
    public CommentDto getCommentById(@PathVariable Long commentId) {
        return commentMapper.convertToDTO(commentServiceImpl.getById(commentId));
    }


    @GetMapping("/get/all")
    public List<CommentDto> getAllComments() {
        return commentMapper.convertToDTOList(commentServiceImpl.getAll());
    }


    @GetMapping("/get/user")
    public List<CommentDto> getCommentsByUserId(Authentication authentication){
        String email = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(email);
        User currentUser = optionalUser.orElseThrow(() -> new IllegalArgumentException("Пользователь не найден: " + email));

        return commentMapper.convertToDTOList(commentServiceImpl.getCommentsByUserId(currentUser.getId()));
    }


    @GetMapping("/get/course/{courseId}")
    public List<CommentDto> getCommentsByCourseId(@PathVariable Long courseId){
        return commentMapper.convertToDTOList(commentServiceImpl.getCommentsByCourseId(courseId));
    }


    @PutMapping("/update/{courseId}/{commentId}")
    public CommentDto updateComment(@RequestBody CommentDto commentDto, @PathVariable Long commentId, Authentication authentication) {
        Comment comment = commentServiceImpl.getById(commentId);
        Optional<User> optionalUser = userRepository.findByEmail(authentication.getName());
        User currentUser = optionalUser.orElseThrow(() -> new IllegalArgumentException("Пользователь не найден: " + authentication.getName()));

        if (!comment.getUser().getId().equals(currentUser.getId())){
            throw new AccessDeniedException("Вы не являетесь владельцем этого комментария.");
        }

        comment.setText(commentDto.getText());
        return commentMapper.convertToDTO(commentServiceImpl.update(comment));
    }


    @DeleteMapping("/delete/{commentId}")
    public void deleteComment(@PathVariable Long commentId, Authentication authentication) {
        Comment existingComment = commentServiceImpl.getById(commentId);
        Optional<User> optionalUser = userRepository.findByEmail(authentication.getName());
        User currentUser = optionalUser.orElseThrow(() -> new IllegalArgumentException("Пользователь не найден: " + authentication.getName()));

        if (!existingComment.getUser().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("Вы не являетесь владельцем этого комментария.");
        }

        commentServiceImpl.delete(commentId);
    }
}
