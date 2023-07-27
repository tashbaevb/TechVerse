package com.example.makersprojectbackend.service.impl;

import com.example.makersprojectbackend.entity.SchoolInfo;
import com.example.makersprojectbackend.entity.User;
import com.example.makersprojectbackend.enums.UserRole;
import com.example.makersprojectbackend.repository.SchoolInfoRepository;
import com.example.makersprojectbackend.repository.UserRepository;
import com.example.makersprojectbackend.service.AuthService;
import com.example.makersprojectbackend.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final SchoolInfoRepository schoolInfoRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(User registrationRequest) {
        User user = new User();
        user.setEmail(registrationRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user.setNameSurname(registrationRequest.getNameSurname());
        user.setUserRole(UserRole.USER);

        SchoolInfo schoolInfo1 = registrationRequest.getSchoolInfo();
        SchoolInfo schoolInfo = new SchoolInfo();
        schoolInfo.setSchoolName(schoolInfo1.getSchoolName());
        schoolInfo.setSchoolNumber(schoolInfo1.getSchoolNumber());
        schoolInfo.setLocation(schoolInfo1.getLocation());
        schoolInfo.setGrade(schoolInfo1.getGrade());

        schoolInfoRepository.save(schoolInfo);
        user.setSchoolInfo(schoolInfo);
        schoolInfo.setUser(user);

        userRepository.save(user);
    }

    @Override
    public boolean isPresentEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent();
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public String resetPassword(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            return "Пользователя с таким адресом эл.почты не существует";
        }

        String resetToken = UUID.randomUUID().toString();
        user.get().setResetToken(resetToken);
        user.get().setResetTokenExpireTime(LocalDateTime.now().plusMinutes(60));
        userRepository.save(user.get());

        String resetUrl = "http://localhost:8082/reset/" + resetToken;
        String emailText = "Здравствуйте! " +
                "\n\nДля сброса пароля перейдите по ссылке: " + resetUrl;

        emailService.sendSimpleMessage(email, "Сброс пароля", emailText);
        return "Ссылка для сброса пароля была отправлена ваш на адрес электронной почты " + email;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public String saveNewPassword(String resetToken, String newPassword) {
        User user = userRepository.findByResetToken(resetToken);
        if (user == null || user.getResetTokenExpireTime().isBefore(LocalDateTime.now()))
            return "Ссылка для сброса пароля устарела";

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetToken(null);
        user.setResetTokenExpireTime(null);
        userRepository.save(user);
        return "Пароль успешно изменен!";
    }
}
