package com.example.makersprojectbackend.controllers;

import com.example.makersprojectbackend.dto.ContactDto;
import com.example.makersprojectbackend.entities.Contact;
import com.example.makersprojectbackend.mappers.ContactMapper;
import com.example.makersprojectbackend.services.impl.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;
    private final ContactMapper contactMapper;

    @PostMapping("/contact-info")
    public ResponseEntity<String> createContact(@RequestBody Contact contact) {
        contactMapper.convertToDto(contactService.createContact(contact));
        return ResponseEntity.ok("Спасибо! Мы скоро с вами свяжемся!");
    }
    @GetMapping("/get/{contactId}")
    public ContactDto getContactById(@PathVariable Long contactId) {
        return contactMapper.convertToDto(contactService.getById(contactId));
    }
    @PutMapping("/contact-info/update")
    public ContactDto update(@RequestBody Contact contact) {
        return contactMapper.convertToDto(contactService.updateContact(contact));
    }
    @GetMapping("/get/all")
    public List<ContactDto> getAllUsers() {
        return contactMapper.convertToDtoList(contactService.getAll());
    }

    @DeleteMapping("/delete/{contactId}")
    public void delete(@PathVariable Long contactId) {
        contactService.delete(contactId);
    }

}
