package com.example.makersprojectbackend.entity;

import com.example.makersprojectbackend.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String fullName;

    @Email
    @NotNull
    private String email;

    @NotNull
    @Size(min = 6, max = 16)
    private String password;

    private String link; //ссылка к фотке

    @NotNull
    private String username;

    @NotNull
    private String school;

    @NotNull
    private UserRole userRole = UserRole.USER;

    @NotNull
    private Boolean activated = false; //подтвердил почту иль нет, по умолчанию всегда нет

}
