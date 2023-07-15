package com.example.makersprojectbackend.controller;

import com.example.makersprojectbackend.dto.CommentDto;
import com.example.makersprojectbackend.entity.Comment;
import com.example.makersprojectbackend.mappers.CommentMapper;
import com.example.makersprojectbackend.service.impl.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentServiceImpl commentServiceImpl;
    private final CommentMapper commentMapper;

    @PostMapping("/create")
    public CommentDto createComment(@RequestBody Comment comment) {
        return commentMapper.convertToDTO(commentServiceImpl.create(comment));
    }

    @GetMapping("/get/{id}")
    public CommentDto getCommentById(@PathVariable Long id) {
        return commentMapper.convertToDTO(commentServiceImpl.getById(id));
    }

    @GetMapping("/get/all")
    public List<CommentDto> getAllComments() {
        return commentMapper.convertToDTOList(commentServiceImpl.getAll());
    }

    @PutMapping("/update")
    public CommentDto updateComment(@RequestBody Comment comment) {
        return commentMapper.convertToDTO(commentServiceImpl.update(comment));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentServiceImpl.delete(id);
    }
}
