package com.example.makersprojectbackend.mappers;

import com.example.makersprojectbackend.dto.course.FreeCourseDto;
import com.example.makersprojectbackend.dto.course.PaidCourseDto;
import com.example.makersprojectbackend.entity.course.Course;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CourseMapper {
    private final ModelMapper mapper;

    public FreeCourseDto convertToFreeCourseDto(Course course) {
        return mapper.map(course, FreeCourseDto.class);
    }

    public List<FreeCourseDto> convertToFreeCourseDtoList(List<Course> courses) {
        List<FreeCourseDto> freeCourseDtoList = new ArrayList<>();
        for (Course c : courses) {
            freeCourseDtoList.add(convertToFreeCourseDto(c));
        }
        return freeCourseDtoList;
    }

    public PaidCourseDto convertToPaidCourseDto(Course course) {
        return mapper.map(course, PaidCourseDto.class);
    }

    public List<PaidCourseDto> convertToPaidCourseDtoList(List<Course> courses) {
        List<PaidCourseDto> paidCourseDtoList = new ArrayList<>();
        for (Course c : courses) {
            paidCourseDtoList.add(convertToPaidCourseDto(c));
        }
        return paidCourseDtoList;
    }
}
