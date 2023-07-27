package com.example.makersprojectbackend.mappers;

import com.example.makersprojectbackend.dto.ContactDto;
import com.example.makersprojectbackend.entity.Contact;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
@RequiredArgsConstructor
public class ContactMapper {
    private final ModelMapper mapper;

    public ContactDto convertToDto(Contact contact) {
        return mapper.map(contact, ContactDto.class);
    }

    public List<ContactDto> convertToDtoList(List<Contact> contacts) {
        List<ContactDto> contactDtoList = new ArrayList<>();
        for (Contact c : contacts) {
            contactDtoList.add(mapper.map(c, ContactDto.class));
        }
        return contactDtoList;
    }
}
