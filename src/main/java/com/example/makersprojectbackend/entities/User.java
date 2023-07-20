package com.example.makersprojectbackend.entities;

import com.example.makersprojectbackend.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    private Long id;
    private String email;
    private String password;
    private String nameSurname;
    @OneToOne
    private SchoolInfo schoolInfo;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    @Column(name = "reset_token")
    private String resetToken;
    @Column(name = "reset_token_expire_time")
    private LocalDateTime resetTokenExpireTime;
}
