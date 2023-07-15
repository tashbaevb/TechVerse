package com.example.makersprojectbackend.mappers;

import com.example.makersprojectbackend.dto.CommentDto;
import com.example.makersprojectbackend.entity.Comment;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentMapper {
    private final ModelMapper mapper;

    public CommentMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public List<CommentDto> convertToDTOList(List<Comment> comments) {
        List<CommentDto> commentDtoList = new ArrayList<>();
        for (Comment c : comments) {
            commentDtoList.add(convertToDTO(c));
        }
        return commentDtoList;
    }


    public CommentDto convertToDTO(Comment comment) {
        return mapper.map(comment, CommentDto.class);
    }
}
