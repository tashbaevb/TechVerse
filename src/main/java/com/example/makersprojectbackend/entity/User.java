package com.example.makersprojectbackend.entity;

import com.example.makersprojectbackend.entity.SchoolInfo;
import com.example.makersprojectbackend.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    String email, password, nameSurname; // Нужно добавить @Email, @Size для password
    @OneToOne
    SchoolInfo schoolInfo;
    @Enumerated(EnumType.STRING)
    UserRole userRole;
    @Column(name = "reset_token")
    String resetToken;
    @Column(name = "reset_token_expire_time")
    LocalDateTime resetTokenExpireTime;
}
