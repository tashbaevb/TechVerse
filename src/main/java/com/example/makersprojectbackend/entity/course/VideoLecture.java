package com.example.makersprojectbackend.entity.course;

import com.example.makersprojectbackend.entity.Comment;
import com.example.makersprojectbackend.entity.quiz.Quiz;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "vids")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class VideoLecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title, description, link;

    @ManyToOne
    Course course;

    @OneToOne(mappedBy = "videoLecture", cascade = CascadeType.ALL)
    Quiz quiz;

    @OneToMany(mappedBy = "videoLecture", cascade = CascadeType.ALL)
    List<Comment> comments;
}
