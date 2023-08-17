package com.example.makersprojectbackend.entity;

import com.example.makersprojectbackend.entity.course.Course;
import com.example.makersprojectbackend.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    Long id;

    String email, password, fullName, schoolName, schoolGrade, schoolLocation;

    Integer schoolNumber;


    @Enumerated(EnumType.STRING)
    UserRole userRole;

    @Column(name = "reset_token")
    String resetToken;

    @Column(name = "reset_token_expire_time")
    LocalDateTime resetTokenExpireTime;

    @Column(name = "refresh_token")
    String refreshToken;

    @Column(name = "refresh_token_expire_time")
    LocalDateTime refreshTokenExpireTime;
}
