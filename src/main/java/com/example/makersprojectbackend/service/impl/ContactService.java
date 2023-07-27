package com.example.makersprojectbackend.service.impl;

import com.example.makersprojectbackend.entity.Contact;
import com.example.makersprojectbackend.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Contact createContact(Contact contact){
        return contactRepository.save(contact);
    }
    public Contact getById(Long id){
        return contactRepository.getById(id);
    }
    public Contact updateContact(Contact newContact){
        Contact oldContact = getById(newContact.getId());
        oldContact.setName(newContact.getName());
        oldContact.setSurname(newContact.getSurname());
        oldContact.setPhoneNumber(newContact.getPhoneNumber());
        return contactRepository.save(oldContact);
    }
    public List<Contact> getAll(){
        return contactRepository.findAll();
    }
    public void delete(Long id){
        contactRepository.deleteById(id);
    }
}
