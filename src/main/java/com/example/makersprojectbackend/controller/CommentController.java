package com.example.makersprojectbackend.controller;

import com.example.makersprojectbackend.dto.CommentDto;
import com.example.makersprojectbackend.entity.Comment;
import com.example.makersprojectbackend.entity.User;
import com.example.makersprojectbackend.mappers.CommentMapper;
import com.example.makersprojectbackend.repository.CommentRepository;
import com.example.makersprojectbackend.repository.UserRepository;
import com.example.makersprojectbackend.repository.course.CourseRepository;
import com.example.makersprojectbackend.service.impl.CommentServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.aspectj.bridge.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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
    private final CourseRepository courseRepository;
    private final SimpMessagingTemplate messagingTemplate;
    private final CommentRepository commentRepository;

    @PostMapping("/create/f/{courseId}")
    public CommentDto createComment1(@RequestBody CommentDto commentDto,
                                    @PathVariable Long courseId,
                                    Authentication authentication) {
        String email = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(email);
        User currentUser = optionalUser.
                orElseThrow(() -> new IllegalArgumentException("Пользователь не найден: " + email));

        Comment comment = commentMapper.convertToEntity(commentDto);
        comment.setCourse(courseRepository.findById(courseId).
                orElseThrow(() -> new IllegalArgumentException("Курс не найден: " + courseId)));
        comment.setUser(currentUser);

        messagingTemplate.convertAndSend("/topic/comments", commentDto);

        return commentMapper.convertToDTO(commentServiceImpl.create(comment, courseId, currentUser.getId()));
    }

    @MessageMapping("/create/{courseId}")
    public CommentDto createComment(@RequestBody CommentDto commentDto,
                                    @PathVariable Long courseId,
                                    Authentication authentication) {
        String email = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(email);
        User currentUser = optionalUser.orElseThrow(() -> new IllegalArgumentException("Пользователь не найден: " + email));

        Comment comment = commentMapper.convertToEntity(commentDto);
        comment.setCourse(courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Курс не найден: " + courseId)));
        comment.setUser(currentUser);

        // Сохраните комментарий в репозитории (commentRepository.save(comment))
        commentRepository.save(comment);

        // Отправляем комментарий через WebSocket.
        messagingTemplate.convertAndSend("/topic/course/" + courseId, commentMapper.convertToDTO(comment));

        return commentMapper.convertToDTO(comment);
    }

    @PostMapping("reply/{courseId}/{parentCommentId}")
    public CommentDto createReplyComment(@RequestBody CommentDto commentDto,
                                         @PathVariable Long courseId,
                                         @PathVariable Long parentCommentId,
                                         Authentication authentication) {
        String email = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(email);
        User currentUser = optionalUser.
                orElseThrow(() -> new IllegalArgumentException("Пользователь не найден: " + email));

        Comment comment = commentMapper.convertToEntity(commentDto);

//        messagingTemplate.convertAndSend("/topic/comments", commentDto);

        return commentMapper.convertToDTO(commentServiceImpl.createReply(comment, courseId, currentUser.getId(), parentCommentId));
    }


    @GetMapping("get/replies/{commentId}")
    public List<CommentDto> getRepliesForComment(@PathVariable Long commentId) {
        List<Comment> replies = commentServiceImpl.getRepliesForComment(commentId);
        return commentMapper.convertToDTOList(replies);
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
    public List<CommentDto> getCommentsByUserId(Authentication authentication) {
        String email = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(email);
        User currentUser = optionalUser.
                orElseThrow(() -> new IllegalArgumentException("Пользователь не найден: " + email));

        return commentMapper.convertToDTOList(commentServiceImpl.getCommentsByUserId(currentUser.getId()));
    }


    //    @GetMapping("/get/course/{courseId}")
//    public List<CommentDto> getCommentsByCourseId(@PathVariable Long courseId) {
//        return commentMapper.convertToDTOList(commentServiceImpl.getCommentsByCourseId(courseId));
//    }
    @GetMapping("/get/course/{courseId}")
    public void getCommentsByCourseId(@PathVariable Long courseId, SimpMessageSendingOperations messagingTemplate) {
        List<Comment> comments = commentServiceImpl.getCommentsByCourseId(courseId);
        List<CommentDto> commentDtos = commentMapper.convertToDTOList(comments);

        // Отправляем комментарии через WebSocket.
        messagingTemplate.convertAndSend("/topic/course/" + courseId, commentDtos);
    }


    @MessageMapping("/get/f/course/{courseId}")
    @SendTo("/topic/activity")
    public Comment getCommentsByCourseId1(Comment comment) {
        return commentRepository.save(comment);
    }


    @PutMapping("/update/{courseId}/{commentId}")
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

        messagingTemplate.convertAndSend("/topic/comments", commentDto);

        return commentMapper.convertToDTO(commentServiceImpl.update(comment));
    }


    @DeleteMapping("/delete/{commentId}")
    public void deleteComment(@PathVariable Long commentId, Authentication authentication) {
        Comment existingComment = commentServiceImpl.getById(commentId);
        Optional<User> optionalUser = userRepository.findByEmail(authentication.getName());
        User currentUser = optionalUser.
                orElseThrow(() -> new IllegalArgumentException("Пользователь не найден: " + authentication.getName()));

        if (!existingComment.getUser().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("Вы не являетесь владельцем этого комментария.");
        }

        commentServiceImpl.delete(commentId);
    }
}
