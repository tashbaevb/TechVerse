package com.example.makersprojectbackend.service;

import com.example.makersprojectbackend.entity.Comment;
import com.example.makersprojectbackend.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment create(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment getById(Long id) {
        return commentRepository.findById(id).orElseThrow();
    }

    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    public Comment update(Comment commentDetails) {
        Comment comment = getById(commentDetails.getId());
        comment.setText(commentDetails.getText());
        return commentRepository.save(comment);
    }

    public void delete(Long id) {
        commentRepository.deleteById(id);
    }
}
