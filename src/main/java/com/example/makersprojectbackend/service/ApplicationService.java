package com.example.makersprojectbackend.service;

import com.example.makersprojectbackend.entity.forms.Application;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ApplicationService {

    List<Application> getAll();

    ResponseEntity<byte[]> exportToExcel(List<Application> applications);
}
