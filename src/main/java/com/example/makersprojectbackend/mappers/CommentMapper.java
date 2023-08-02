package com.example.makersprojectbackend.mappers;

import com.example.makersprojectbackend.dto.CommentDto;
import com.example.makersprojectbackend.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CommentMapper {

    private final ModelMapper mapper;

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

    public Comment convertToEntity(CommentDto commentDto){
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setText(commentDto.getText());
        return comment;
    }
}
