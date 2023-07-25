package com.example.makersprojectbackend.entity;

import com.example.makersprojectbackend.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    String fullName;

    @Email
    @NotNull
    String email;

    @NotNull
    @Size(min = 6, max = 16)
    String password;

    String link; //ссылка к фотке

    @NotNull
    String username, school;
//
//    @NotNull
//    String school;

    @NotNull
    UserRole userRole = UserRole.USER;

    @NotNull
    Boolean activated = false; //подтвердил почту иль нет, по умолчанию всегда нет
}
