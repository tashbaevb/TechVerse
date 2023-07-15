package com.example.makersprojectbackend.services;

import com.example.makersprojectbackend.dto.UserDto;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

public interface AuthService {

    void register(UserDto registrationRequest);

    boolean isPresentEmail(String email);

    @Transactional(isolation = Isolation.SERIALIZABLE)
    String resetPassword(String email);

    @Transactional(isolation = Isolation.SERIALIZABLE)
    String saveNewPassword(String resetToken, String newPassword);
}
