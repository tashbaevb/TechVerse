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

    @PostMapping("create/{courseId}")
    public CommentDto createComment(@RequestBody CommentDto commentDto,
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

        return commentMapper.convertToDTO(commentServiceImpl.create(comment, courseId, currentUser.getId()));
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
        return commentMapper.convertToDTO(commentServiceImpl.createReply(comment, courseId, currentUser.getId(), parentCommentId));
    }


    @GetMapping("get/replies/{commentId}")
    public List<CommentDto> getRepliesForComment(@PathVariable Long commentId) {
        List<Comment> replies = commentServiceImpl.getRepliesForComment(commentId);
        return commentMapper.convertToDTOList(replies);
    }


//
//    @PostMapping("/favorite/add/{commentId}")
//    public ResponseEntity<String> addToFavorites(@PathVariable Long commentId, Authentication authentication) {
//        String email = authentication.getName();
//        Optional<User> optionalUser = userRepository.findByEmail(email);
//        User currentUser = optionalUser.orElseThrow(() -> new IllegalArgumentException("Пользователь не найден: " + email));
//
//        commentServiceImpl.addToFavorites(commentId, currentUser.getId());
//        return ResponseEntity.ok("Комментарий добавлен в избранное");
//    }
//
//    @PostMapping("/favorite/remove/{commentId}")
//    public ResponseEntity<String> removeFromFavorites(@PathVariable Long commentId, Authentication authentication) {
//        String email = authentication.getName();
//        Optional<User> optionalUser = userRepository.findByEmail(email);
//        User currentUser = optionalUser.orElseThrow(() -> new IllegalArgumentException("Пользователь не найден: " + email));
//
//        commentServiceImpl.removeFromFavorites(commentId, currentUser.getId());
//        return ResponseEntity.ok("Комментарий удален из избранного");
//    }
//
//
//    @GetMapping("/favorite/count/{commentId}")
//    public ResponseEntity<Integer> getFavoriteUserCount(@PathVariable Long commentId) {
//        int count = commentServiceImpl.getFavoriteUserCount(commentId);
//        return ResponseEntity.ok(count);
//    }
//
//    @GetMapping("/favorite/users/{commentId}")
//    public ResponseEntity<List<UserDto>> getFavoriteUsers(@PathVariable Long commentId) {
//        List<UserDto> favoriteUsers = commentServiceImpl.getFavoriteUsers(commentId);
//        return ResponseEntity.ok(favoriteUsers);
//    }
//
//    @GetMapping("/favorite/comments/{userId}")
//    public ResponseEntity<List<CommentDto>> getFavoriteCommentsByUser(@PathVariable Long userId) {
//        List<CommentDto> favoriteComments = commentServiceImpl.getFavoriteCommentsByUser(userId);
//        return ResponseEntity.ok(favoriteComments);
//    }


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


    @GetMapping("/get/course/{courseId}")
    public List<CommentDto> getCommentsByCourseId(@PathVariable Long courseId) {
        return commentMapper.convertToDTOList(commentServiceImpl.getCommentsByCourseId(courseId));
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
