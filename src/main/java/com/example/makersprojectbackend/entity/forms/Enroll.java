package com.example.makersprojectbackend.entity.forms;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "enrolls")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Enroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String fullName, phoneNumber, courseName;

//    private String phoneNumber;
//
//    private String courseName; //имя курса куда хочет записаться заявитель

    LocalDateTime dateOfCreation;
}
