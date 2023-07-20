package com.example.makersprojectbackend.mappers;

import com.example.makersprojectbackend.dto.UserDto;
import com.example.makersprojectbackend.entities.User;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final ModelMapper mapper;

    public UserDto convertToDto(User user) {
        return mapper.map(user, UserDto.class);
    }
    public UserDto convertToDtoOpt(Optional<User> user) {
        return mapper.map(user, UserDto.class);
    }


    public List<UserDto> convertToDtoList(List<User> users) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User u : users) {
            userDtoList.add(mapper.map(u, UserDto.class));
        }
        return userDtoList;
    }
}
