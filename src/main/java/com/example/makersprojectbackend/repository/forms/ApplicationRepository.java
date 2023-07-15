package com.example.makersprojectbackend.repository.forms;

import com.example.makersprojectbackend.entity.forms.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

}
