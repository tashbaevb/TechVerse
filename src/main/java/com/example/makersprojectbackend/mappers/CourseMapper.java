package com.example.makersprojectbackend.mappers;

import com.example.makersprojectbackend.dto.course.FreeCourseDto;
import com.example.makersprojectbackend.dto.course.PaidCourseDto;
import com.example.makersprojectbackend.entity.course.Course;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class CourseMapper {
    private ModelMapper mapper;

    public FreeCourseDto convertToFreeCourseDto(Course course) {
        FreeCourseDto freeCourseDto = mapper.map(course, FreeCourseDto.class);
        if (course.getFile() != null){
            freeCourseDto.setFile(course.getFile().getFile());
        }
        return freeCourseDto;
    }

    public List<FreeCourseDto> convertToFreeCourseDtoList(List<Course> courses) {
        List<FreeCourseDto> freeCourseDtoList = new ArrayList<>();
        for (Course c : courses) {
            freeCourseDtoList.add(convertToFreeCourseDto(c));
        }
        return freeCourseDtoList;
    }

    public PaidCourseDto convertToPaidCourseDto(Course course) {
        PaidCourseDto paidCourseDto = mapper.map(course, PaidCourseDto.class);
        if (course.getFile() != null) {
            paidCourseDto.setFile(course.getFile().getFile());
        }
        return paidCourseDto;
    }


    public Course convertToEntity(FreeCourseDto course) {
        return mapper.map(course, Course.class);
    }

    public Course convertToEntity(PaidCourseDto course) {
        return mapper.map(course, Course.class);
    }

    public List<PaidCourseDto> convertToPaidCourseDtoList(List<Course> courses) {
        List<PaidCourseDto> paidCourseDtoList = new ArrayList<>();
        for (Course c : courses) {
            paidCourseDtoList.add(convertToPaidCourseDto(c));
        }
        return paidCourseDtoList;
    }
}
