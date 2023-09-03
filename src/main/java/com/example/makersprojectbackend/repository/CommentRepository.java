package com.example.makersprojectbackend.repository;

import com.example.makersprojectbackend.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByUserId(Long userId);

    List<Comment> findByVideoLectureId(Long videoLectureId);

    List<Comment> findByParentComment(Comment parentComment);
}

