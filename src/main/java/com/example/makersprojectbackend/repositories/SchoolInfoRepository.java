package com.example.makersprojectbackend.repositories;

import com.example.makersprojectbackend.entities.SchoolInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolInfoRepository extends JpaRepository<SchoolInfo, Long> {
}
