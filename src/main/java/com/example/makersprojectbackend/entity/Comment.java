package com.example.makersprojectbackend.entity;

import com.example.makersprojectbackend.entity.course.Course;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "comments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String text;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    Course course;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;


    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    Comment parentComment; // ссылка на родительский комментарий
}
