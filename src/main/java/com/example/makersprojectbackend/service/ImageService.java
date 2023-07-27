package com.example.makersprojectbackend.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    String saveToTheFileSystem(MultipartFile image) throws IOException;
}
