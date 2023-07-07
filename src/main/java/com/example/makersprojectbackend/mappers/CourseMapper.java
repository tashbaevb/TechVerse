package com.example.makersprojectbackend.mappers;

import com.example.makersprojectbackend.dto.CourseDto;
import com.example.makersprojectbackend.entity.Course;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseMapper {
    private final ModelMapper mapper;

    public CourseMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public List<CourseDto> convertToDTOList(List<Course> courses) {
        List<CourseDto> courseDtoList = new ArrayList<>();
        for (Course c : courses) {
            courseDtoList.add(convertToDTO(c));
        }
        return courseDtoList;
    }

    public Course convertToEntity(CourseDto courseDto) {
        return mapper.map(courseDto, Course.class);
    }

    public CourseDto convertToDTO(Course course) {
        return mapper.map(course, CourseDto.class);
    }
}
