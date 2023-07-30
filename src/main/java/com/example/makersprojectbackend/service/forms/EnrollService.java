package com.example.makersprojectbackend.service.forms;

import com.example.makersprojectbackend.entity.forms.Enroll;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EnrollService {

    List<Enroll> getAll();

    ResponseEntity<byte[]> exportToExcel(List<Enroll> enrolls);
}
