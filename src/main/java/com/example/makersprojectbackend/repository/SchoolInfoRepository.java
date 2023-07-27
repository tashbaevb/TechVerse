package com.example.makersprojectbackend.repository;

import com.example.makersprojectbackend.entity.SchoolInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolInfoRepository extends JpaRepository<SchoolInfo, Long> {
}
