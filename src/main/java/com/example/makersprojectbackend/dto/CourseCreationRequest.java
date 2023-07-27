package com.example.makersprojectbackend.dto;

import com.example.makersprojectbackend.entity.Course;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;


public class CourseCreationRequest {
    private Course course;
    private MultipartFile image;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
