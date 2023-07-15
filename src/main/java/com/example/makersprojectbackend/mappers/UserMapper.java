package com.example.makersprojectbackend.mappers;

import com.example.makersprojectbackend.dto.UserDto;
import com.example.makersprojectbackend.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {
    private final ModelMapper mapper;

    public UserMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public List<UserDto> convertToDTOList(List<User> users) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User u : users) {
            userDtoList.add(convertToDTO(u));
        }
        return userDtoList;
    }


    public UserDto convertToDTO(User user) {
        return mapper.map(user, UserDto.class);
    }
}
