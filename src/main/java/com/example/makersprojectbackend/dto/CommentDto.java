package com.example.makersprojectbackend.dto;

import lombok.Data;

@Data
public class CommentDto {

    private String text;

    private UserDto author;
}
