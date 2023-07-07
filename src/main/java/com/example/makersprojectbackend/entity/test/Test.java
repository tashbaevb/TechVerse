package com.example.makersprojectbackend.entity.test;

import com.example.makersprojectbackend.entity.VideoLecture;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tests")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "test")
    private List<Question> questions;

    @OneToOne
    private VideoLecture videoLecture;

}
