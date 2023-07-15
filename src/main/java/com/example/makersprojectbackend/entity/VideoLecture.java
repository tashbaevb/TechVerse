package com.example.makersprojectbackend.entity;

import com.example.makersprojectbackend.entity.test.Quiz;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vids")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VideoLecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String link;

    @ManyToOne
    private Course course;

    @OneToOne(mappedBy = "videoLecture")
    private Quiz quiz;
}
