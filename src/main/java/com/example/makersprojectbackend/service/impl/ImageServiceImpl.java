package com.example.makersprojectbackend.service.impl;

import com.example.makersprojectbackend.service.ImageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {
    @Value("${upload.folder}")
    String uploadFolder;
    @Override
    public String saveToTheFileSystem(MultipartFile image) throws IOException {
        String resultUrl = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
        image.transferTo(new File(uploadFolder + resultUrl));
        return resultUrl;
    }
}