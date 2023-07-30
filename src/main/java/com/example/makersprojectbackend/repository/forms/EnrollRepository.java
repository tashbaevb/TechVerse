package com.example.makersprojectbackend.repository.forms;

import com.example.makersprojectbackend.entity.forms.Enroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollRepository extends JpaRepository<Enroll, Long> {

}
