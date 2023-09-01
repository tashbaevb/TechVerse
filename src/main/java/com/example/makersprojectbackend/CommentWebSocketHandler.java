package com.example.makersprojectbackend;

import com.example.makersprojectbackend.dto.CommentDto;
import com.example.makersprojectbackend.entity.Comment;
import com.example.makersprojectbackend.entity.User;
import com.example.makersprojectbackend.mappers.CommentMapper;
import com.example.makersprojectbackend.repository.UserRepository;
import com.example.makersprojectbackend.repository.course.CourseRepository;
import com.example.makersprojectbackend.service.CommentService;
import com.example.makersprojectbackend.service.impl.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Component
@Controller
@RequiredArgsConstructor
public class CommentWebSocketHandler {

    private final CommentServiceImpl commentServiceImpl;
    private final CommentMapper commentMapper;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final SimpMessagingTemplate messagingTemplate;
    private final CommentService commentService;

    @MessageMapping("/new-comment")
    @SendTo("/topic/comments")
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

        messagingTemplate.convertAndSend("/topic/comments", commentDto);

        return commentMapper.convertToDTO(commentServiceImpl.create(comment, courseId, currentUser.getId()));
    }


    @MessageMapping("/reply")
    @SendTo("/topic/comments")
    public CommentDto createReplyComment(@RequestBody CommentDto commentDto,
                                         @PathVariable Long courseId,
                                         @PathVariable Long parentCommentId,
                                         Authentication authentication) {
        String email = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(email);
        User currentUser = optionalUser.
                orElseThrow(() -> new IllegalArgumentException("Пользователь не найден: " + email));

        Comment comment = commentMapper.convertToEntity(commentDto);

        messagingTemplate.convertAndSend("/topic/comments", commentDto);

        return commentMapper.convertToDTO(commentServiceImpl.createReply(comment, courseId, currentUser.getId(), parentCommentId));
    }


    @MessageMapping("/get-replies/{commentId}")
    public void getRepliesForComment(@DestinationVariable Long commentId) {
        List<Comment> replies = commentService.getRepliesForComment(commentId);
        List<CommentDto> replyDtos = commentMapper.convertToDTOList(replies);

        messagingTemplate.convertAndSend("/topic/replies/" + commentId, replyDtos);
    }


    @MessageMapping("/get-comments/{courseId}")
    public void getCommentsForCourse(@DestinationVariable Long courseId) {
        List<Comment> comments = commentService.getCommentsByCourseId(courseId);
        List<CommentDto> commentDtos = commentMapper.convertToDTOList(comments);

        messagingTemplate.convertAndSend("/topic/comments/" + courseId, commentDtos);
    }


    @MessageMapping("/update-comment")
    @SendTo("/topic/comments")
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


    @MessageMapping("/delete-comment")
    @SendTo("/topic/comments")
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

//    @MessageMapping("/new-comment")
//    @SendTo("/topic/comments")
//    public CommentDto createComment(CommentDto commentDto, Authentication authentication) {
//        String email = authentication.getName();
//        User currentUser = getUserFromEmail(email);
//
//        Comment comment = commentMapper.convertToEntity(commentDto);
//        comment.setUser(currentUser);
//
//        Course course = getCourseById(commentDto.getId());
//        comment.setCourse(course);
//
//        Comment createdComment = commentService.create(comment, course.getId(), currentUser.getId());
//
//        return commentMapper.convertToDTO(createdComment);
//    }
//
//    @MessageMapping("/update-comment")
//    @SendTo("/topic/comments")
//    public CommentDto updateComment(CommentDto commentDto, Authentication authentication) {
//        String email = authentication.getName();
//        User currentUser = getUserFromEmail(email);
//
//        Comment comment = commentMapper.convertToEntity(commentDto);
//        comment.setUser(currentUser);
//
//        Comment updatedComment = commentService.update(comment);
//
//        return commentMapper.convertToDTO(updatedComment);
//    }
//
//    @MessageMapping("/delete-comment")
//    @SendTo("/topic/comments")
//    public Long deleteComment(Long commentId, Authentication authentication) {
//        String email = authentication.getName();
//        User currentUser = getUserFromEmail(email);
//
//        commentService.delete(commentId, currentUser.getId());
//
//        return commentId;
//    }

}

