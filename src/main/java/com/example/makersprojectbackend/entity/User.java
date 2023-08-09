package com.example.makersprojectbackend.entity;

import com.example.makersprojectbackend.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

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

    private String email;

    private String password;

    private String fullName;

    private Integer schoolNumber;

    private String schoolName;

    private String schoolGrade;

    private String schoolLocation; // Нужно добавить @Email, @Size для password

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Column(name = "reset_token")
    private String resetToken;

    @Column(name = "reset_token_expire_time")
    private LocalDateTime resetTokenExpireTime;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "refresh_token_expire_time")
    private LocalDateTime refreshTokenExpireTime;
}
