package com.example.makersprojectbackend.dto;


import com.example.makersprojectbackend.dto.course.LectureDto;
import com.example.makersprojectbackend.dto.course.VideoLectureDto;
import com.example.makersprojectbackend.enums.CourseDirection;
import com.example.makersprojectbackend.enums.CourseType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter

public class FavoriteDto {
    Long id;
    String name, description;
    CourseType courseType;
    List<LectureDto> lectures;
    List<VideoLectureDto> videoLectures;
    CourseDirection courseDirection;
    byte[] file;
    Integer lectureQuantity;
    Integer duration;

    // Поля для платного курса
    BigDecimal price;
}
