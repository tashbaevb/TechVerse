package com.example.makersprojectbackend.controller;

import com.example.makersprojectbackend.dto.CommentDto;
import com.example.makersprojectbackend.entity.Comment;
import com.example.makersprojectbackend.entity.User;
import com.example.makersprojectbackend.entity.course.VideoLecture;
import com.example.makersprojectbackend.mappers.CommentMapper;
import com.example.makersprojectbackend.repository.UserRepository;
import com.example.makersprojectbackend.repository.course.VideoLectureRepository;
import com.example.makersprojectbackend.service.impl.CommentServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
@Tag(name = "Comment", description = "CRUD комментов, Ответ на другой комментарий")
public class CommentController {

    private final CommentServiceImpl commentServiceImpl;
    private final CommentMapper commentMapper;
    private final UserRepository userRepository;
    private final VideoLectureRepository videoLectureRepository;


    @PostMapping("/create/{videoLectureId}")
    public CommentDto createComment(@RequestBody CommentDto commentDto,
                                    @PathVariable Long videoLectureId,
                                    Authentication authentication) {
        String email = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(email);
        User currentUser = optionalUser
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден: " + email));

        VideoLecture videoLecture = videoLectureRepository.findById(videoLectureId)
                .orElseThrow(() -> new IllegalArgumentException("Видеолекция не найдена: " + videoLectureId));

        Comment comment = commentMapper.convertToEntity(commentDto);
        comment.setVideoLecture(videoLecture);
        comment.setUser(currentUser);

        return commentMapper.convertToDTO(commentServiceImpl.create(comment, videoLectureId, currentUser.getId()));
    }


    @PostMapping("/reply/{videoLectureId}/{parentCommentId}")
    public CommentDto createReplyComment(@RequestBody CommentDto commentDto,
                                                     @PathVariable Long videoLectureId,
                                                     @PathVariable Long parentCommentId,
                                                     Authentication authentication) {
        String email = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(email);
        User currentUser = optionalUser
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден: " + email));

        Comment comment = commentMapper.convertToEntity(commentDto);

        return commentMapper.convertToDTO(commentServiceImpl.createReply(comment, videoLectureId, currentUser.getId(), parentCommentId));
    }


    @GetMapping("/get/replies/{commentId}")
    public List<CommentDto> getRepliesComment(@PathVariable Long commentId) {
        List<Comment> replies = commentServiceImpl.getRepliesForComment(commentId);
        return commentMapper.convertToDTOList(replies);
    }


    @GetMapping("/get/{commentId}")
    public CommentDto getCommentById(@PathVariable Long commentId) {
        return commentMapper.convertToDTO(commentServiceImpl.getById(commentId));
    }


    @GetMapping("/get/user")
    public List<CommentDto> getCommentsByUserId(Authentication authentication) {
        String email = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(email);
        User currentUser = optionalUser
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден: " + email));

        return commentMapper.convertToDTOList(commentServiceImpl.getCommentsByUserId(currentUser.getId()));
    }


    @GetMapping("/get/videoLecture/{videoLectureId}")
    public List<CommentDto> getCommentsByVideoLectureId(@PathVariable Long videoLectureId) {
        VideoLecture videoLecture = videoLectureRepository.findById(videoLectureId)
                .orElseThrow(() -> new IllegalArgumentException("Видеолекция не найдена: " + videoLectureId));

        List<Comment> comments = commentServiceImpl.getCommentsByVideoLectureId(videoLectureId);
        return commentMapper.convertToDTOList(comments);    }


    @PutMapping("/update/{videoLectureId}/{commentId}")
    public CommentDto updateComment(@RequestBody CommentDto commentDto,
                                    @PathVariable Long commentId,
                                    Authentication authentication) {
        Comment comment = commentServiceImpl.getById(commentId);
        Optional<User> optionalUser = userRepository.findByEmail(authentication.getName());
        User currentUser = optionalUser.
                orElseThrow(() -> new IllegalArgumentException("Пользователь не найден: " + authentication.getName()));

        if (!comment.getUser().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("Вы не являетесь владельцем этого комментария.");
        }

        comment.setText(commentDto.getText());

        return commentMapper.convertToDTO(commentServiceImpl.update(comment));
    }


    @DeleteMapping("/delete/{commentId}")
    public void deleteComment(@PathVariable Long commentId, Authentication authentication) {
        Comment existingComment = commentServiceImpl.getById(commentId);
        Optional<User> optionalUser = userRepository.findByEmail(authentication.getName());
        User currentUser = optionalUser
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден: " + authentication.getName()));

        if (!existingComment.getUser().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("Вы не являетесь владельцем этого комментария.");
        }

        commentServiceImpl.delete(commentId);
    }
}
