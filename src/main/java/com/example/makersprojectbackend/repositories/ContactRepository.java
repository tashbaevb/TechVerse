package com.example.makersprojectbackend.repositories;

import com.example.makersprojectbackend.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact,Long> {
}
