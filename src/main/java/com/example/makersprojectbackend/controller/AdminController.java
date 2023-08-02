package com.example.makersprojectbackend.controller;

import com.example.makersprojectbackend.dto.CourseDto;
import com.example.makersprojectbackend.entity.Course;
import com.example.makersprojectbackend.entity.forms.Feedback;
import com.example.makersprojectbackend.mappers.CourseMapper;
import com.example.makersprojectbackend.models.Enroll;
import com.example.makersprojectbackend.service.EnrollService;
import com.example.makersprojectbackend.service.ImageService;
import com.example.makersprojectbackend.service.impl.ApplicationServiceImpl;
import com.example.makersprojectbackend.service.impl.CourseServiceImpl;
import com.example.makersprojectbackend.service.impl.FeedbackServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final CourseServiceImpl courseServiceImpl;
    private final CourseMapper courseMapper;
    private final ApplicationServiceImpl applicationServiceImpl;
    private final FeedbackServiceImpl feedbackServiceImpl;
    private final ImageService imageService;
    private final EnrollService enrollService;
    @Value("${upload.folder}")
    String uploadPath;

    @GetMapping("/getImage")
    public ResponseEntity<Resource> getImage(@RequestParam("fileName") String fileName) throws IOException {
        Path path = Paths.get(uploadPath + fileName);
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, Files.probeContentType(path));
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + fileName);
        headers.add(HttpHeaders.CACHE_CONTROL, "max-age=3600");
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(Files.size(path))
                .body(resource);
    }

    // СПИСКИ
//    @GetMapping("/get/apps")
//    public List<Application> getApplications() {
//        return applicationServiceImpl.getAll();
//    }
    @GetMapping("/enrolls")
    public List<Enroll> getAllEnrolls() {
        return enrollService.getAllEnrolls();
    }

    @GetMapping("/download/apps")
    public ResponseEntity<byte[]> downloadApplicationsList() {
        return applicationServiceImpl.exportToExcel(applicationServiceImpl.getAll());
    }

    @GetMapping("/get/feedbacks")
    public List<Feedback> getFeedbacks() {
        return feedbackServiceImpl.getAll();
    }

    @GetMapping("/download/feedbacks")
    public ResponseEntity<byte[]> downloadFeedbacksList() {
        return feedbackServiceImpl.exportToExcel(feedbackServiceImpl.getAll());
    }

    //  Бесплатные КУРСЫ
//    @PostMapping("/course/create")
//    public CourseDto createCourse(
//            @RequestBody Course course,
//            @RequestBody MultipartFile image)throws IOException {
//        String imageUrl = imageService.saveToTheFileSystem(image);
//        course.setPhotoUrl(imageUrl);
//        return courseMapper.convertToDTO(courseServiceImpl.create(course));
//    }
    @PostMapping("/course/create")
    public CourseDto createCourse(Course course) {
        return courseMapper.convertToDTO(courseServiceImpl.create(course));
    }

    @GetMapping("/course/get/{id}")
    public CourseDto getCourseById(@PathVariable Long id) {
        return courseMapper.convertToDTO(courseServiceImpl.getById(id));
    }

    @GetMapping("/course/get/all")
    public List<CourseDto> getAllCourses() {
        return courseMapper.convertToDTOList(courseServiceImpl.getAll());
    }

    @PutMapping("/course/update")
    public CourseDto updateCourse(@RequestBody Course course) {
        return courseMapper.convertToDTO(courseServiceImpl.update(course));
    }

    @DeleteMapping("/course/delete/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseServiceImpl.delete(id);
    }

    @PutMapping("/course/lecture/add/{courseId}/{lectureId}")
    public CourseDto addLectureToCourse(@PathVariable Long courseId, @PathVariable Long lectureId) {
        return courseMapper.convertToDTO(courseServiceImpl.addLecture(courseId, lectureId));
    }

    @DeleteMapping("/course/lecture/remove/{courseId}/{lectureId}")
    public CourseDto removeLectureToCourse(@PathVariable Long courseId, @PathVariable Long lectureId) {
        return courseMapper.convertToDTO(courseServiceImpl.removeLecture(courseId, lectureId));
    }

    @PutMapping("/course/v/lecture/add/{courseId}/{lectureId}")
    public CourseDto addVideoLectureToCourse(@PathVariable Long courseId, @PathVariable Long lectureId) {
        return courseMapper.convertToDTO(courseServiceImpl.addVideoLecture(courseId, lectureId));
    }

    @DeleteMapping("/course/v/lecture/remove/{courseId}/{lectureId}")
    public CourseDto removeVideoLectureToCourse(@PathVariable Long courseId, @PathVariable Long lectureId) {
        return courseMapper.convertToDTO(courseServiceImpl.removeVideoLecture(courseId, lectureId));
    }
}
