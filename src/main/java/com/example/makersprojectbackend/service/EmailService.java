package com.example.makersprojectbackend.service;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
}
