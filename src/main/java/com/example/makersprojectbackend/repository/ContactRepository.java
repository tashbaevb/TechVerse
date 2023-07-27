package com.example.makersprojectbackend.repository;

import com.example.makersprojectbackend.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact,Long> {
}
