package com.example.makersprojectbackend.services;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
}
