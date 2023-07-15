package com.example.makersprojectbackend.service.impl;

import com.example.makersprojectbackend.entity.Comment;
import com.example.makersprojectbackend.repository.CommentRepository;
import com.example.makersprojectbackend.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment create(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment getById(Long id) {
        return commentRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment update(Comment commentDetails) {
        Comment comment = getById(commentDetails.getId());
        comment.setText(commentDetails.getText());
        return commentRepository.save(comment);
    }

    @Override
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }
}
