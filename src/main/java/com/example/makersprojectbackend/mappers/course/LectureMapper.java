package com.example.makersprojectbackend.mappers.course;

import com.example.makersprojectbackend.dto.course.LectureDto;
import com.example.makersprojectbackend.dto.course.VideoLectureDto;
import com.example.makersprojectbackend.entity.course.Lecture;
import com.example.makersprojectbackend.entity.course.VideoLecture;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class LectureMapper {
    private ModelMapper mapper;

    public LectureDto convertToDto(Lecture lecture) {
        return mapper.map(lecture, LectureDto.class);
    }

    public VideoLectureDto convertToDto(VideoLecture videoLecture) {
        return mapper.map(videoLecture, VideoLectureDto.class);
    }

    public Lecture convertToEntity(LectureDto lectureDto) {
        return mapper.map(lectureDto, Lecture.class);
    }

    public VideoLecture convertToEntity(VideoLectureDto videoLectureDto) {
        return mapper.map(videoLectureDto, VideoLecture.class);
    }

    public List<LectureDto> convertToDtoList(List<Lecture> lectures) {
        List<LectureDto> lectureDtoList = new ArrayList<>();
        for (Lecture l : lectures) {
            lectureDtoList.add(convertToDto(l));
        }
        return lectureDtoList;
    }

    public List<VideoLectureDto> convertToVDtoList(List<VideoLecture> videoLectures) {
        List<VideoLectureDto> videoLectureDtoList = new ArrayList<>();
        for (VideoLecture l : videoLectures) {
            videoLectureDtoList.add(convertToDto(l));
        }
        return videoLectureDtoList;
    }
}
