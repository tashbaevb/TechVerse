package com.example.makersprojectbackend.repository;

import com.example.makersprojectbackend.entity.test.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
}
