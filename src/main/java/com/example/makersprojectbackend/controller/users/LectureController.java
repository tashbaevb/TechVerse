package com.example.makersprojectbackend.controller.users;

import com.example.makersprojectbackend.dto.course.LectureDto;
import com.example.makersprojectbackend.dto.course.VideoLectureDto;
import com.example.makersprojectbackend.entity.course.Lecture;
import com.example.makersprojectbackend.entity.course.VideoLecture;
import com.example.makersprojectbackend.mappers.course.LectureMapper;
import com.example.makersprojectbackend.service.course.LectureService;
import com.example.makersprojectbackend.service.course.VideoLectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/l")
@RequiredArgsConstructor
public class LectureController {
    private final LectureService lectureService;
    private final VideoLectureService videoLectureService;
    private final LectureMapper lectureMapper;

    @PostMapping("/create")
    public LectureDto createLecture(@RequestBody LectureDto lectureDto) {
        Lecture lecture = lectureMapper.convertToEntity(lectureDto);
        return lectureMapper.convertToDto(lectureService.create(lecture));
    }

    @GetMapping("/get/{id}")
    public LectureDto getLecture(@PathVariable Long id) {
        return lectureMapper.convertToDto(lectureService.getById(id));
    }

    @GetMapping("/get/all")
    public List<LectureDto> getAllLectures() {
        return lectureMapper.convertToDtoList(lectureService.getAll());
    }

    @PutMapping("/update")
    public LectureDto updateLecture(@RequestBody LectureDto lectureDto) {
        Lecture lecture = lectureMapper.convertToEntity(lectureDto);
        return lectureMapper.convertToDto(lectureService.update(lecture));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteLecture(@PathVariable Long id) {
        lectureService.delete(id);
    }

    /////////////////////////////

    @PostMapping("/v/create")
    public VideoLectureDto createVideoLecture(@RequestBody VideoLectureDto videoLectureDto) {
        VideoLecture videoLecture = lectureMapper.convertToEntity(videoLectureDto);
        return lectureMapper.convertToDto(videoLectureService.create(videoLecture));
    }

    @GetMapping("/v/get/{id}")
    public VideoLectureDto getVideoLecture(@PathVariable Long id) {
        return lectureMapper.convertToDto(videoLectureService.getById(id));
    }

    @GetMapping("/v/get/all")
    public List<VideoLectureDto> getAllVideoLectures() {
        return lectureMapper.convertToVDtoList(videoLectureService.getAll());
    }

    @PutMapping("/v/update")
    public VideoLectureDto updateVideoLecture(@RequestBody VideoLectureDto videoLectureDto) {
        VideoLecture videoLecture = lectureMapper.convertToEntity(videoLectureDto);
        return lectureMapper.convertToDto(videoLectureService.update(videoLecture));
    }

    @DeleteMapping("/v/delete/{id}")
    public void deleteVideoLecture(@PathVariable Long id) {
        videoLectureService.delete(id);
    }
}
