package com.example.makersprojectbackend.service.security;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
}
