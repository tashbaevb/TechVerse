package com.example.makersprojectbackend.entity;

import com.example.makersprojectbackend.entity.test.Quiz;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
    String link;

    @ManyToOne
    Course course;

    @OneToOne(mappedBy = "videoLecture")
    Quiz quiz;
}
