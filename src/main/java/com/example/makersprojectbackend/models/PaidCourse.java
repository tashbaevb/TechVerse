package com.example.makersprojectbackend.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Entity
@Table(name = "paidcourses")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaidCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String name;
    double duration, fee;
    int numOfLectures;
    char data;
//    boolean favorite;

    @ManyToOne
    @JoinColumn(name = "enroll_id")
    Enroll enroll;
}
