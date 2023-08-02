package com.example.makersprojectbackend.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class CommentDto {

    Long id;
    String text;
}
