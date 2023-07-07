package com.example.makersprojectbackend.controller;

import com.example.makersprojectbackend.dto.CommentDto;
import com.example.makersprojectbackend.entity.Comment;
import com.example.makersprojectbackend.mappers.CommentMapper;
import com.example.makersprojectbackend.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper commentMapper;

    public CommentController(CommentService commentService, CommentMapper commentMapper) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
    }

    @PostMapping("/create")
    public CommentDto createComment(@RequestBody Comment comment) {
        return commentMapper.convertToDTO(commentService.create(comment));
    }

    @GetMapping("/get/{id}")
    public CommentDto getCommentById(@PathVariable Long id) {
        return commentMapper.convertToDTO(commentService.getById(id));
    }

    @GetMapping("/get/all")
    public List<CommentDto> getAllComments() {
        return commentMapper.convertToDTOList(commentService.getAll());
    }

    @PutMapping("/update")
    public CommentDto updateComment(@RequestBody Comment comment) {
        return commentMapper.convertToDTO(commentService.update(comment));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.delete(id);
    }
}
