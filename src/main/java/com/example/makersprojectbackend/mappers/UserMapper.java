package com.example.makersprojectbackend.mappers;

import com.example.makersprojectbackend.dto.UserDto;
//<<<<<<< HEAD
//=======
//import com.example.makersprojectbackend.entity.User;
import com.example.makersprojectbackend.entity.User;
import lombok.RequiredArgsConstructor;

//>>>>>>> adelina
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;


import java.util.ArrayList;
import java.util.List;
//<<<<<<< HEAD

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final ModelMapper mapper;

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
//=======
//
//    @Component
//    @RequiredArgsConstructor
//    public class UserMapper {
//        private final ModelMapper mapper;
//
//        public UserDto convertToDto(User user) {
//            return mapper.map(user, UserDto.class);
//        }
//
//        public UserDto convertToDtoOpt(Optional<User> user) {
//            return mapper.map(user, UserDto.class);
//        }
//
//
//        public List<UserDto> convertToDtoList(List<User> users) {
//            List<UserDto> userDtoList = new ArrayList<>();
//            for (User u : users) {
//                userDtoList.add(mapper.map(u, UserDto.class));
//            }
//            return userDtoList;
//        }
////>>>>>>>adelina
//    }
