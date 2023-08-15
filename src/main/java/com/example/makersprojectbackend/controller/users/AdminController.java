package com.example.makersprojectbackend.controller.users;

import com.example.makersprojectbackend.dto.UserDto;
import com.example.makersprojectbackend.dto.course.FreeCourseDto;
import com.example.makersprojectbackend.dto.course.PaidCourseDto;
import com.example.makersprojectbackend.entity.course.Course;
import com.example.makersprojectbackend.entity.forms.Enroll;
import com.example.makersprojectbackend.entity.forms.Feedback;
import com.example.makersprojectbackend.mappers.CourseMapper;
import com.example.makersprojectbackend.mappers.UserMapper;
import com.example.makersprojectbackend.service.UserService;
import com.example.makersprojectbackend.service.course.CourseService;
import com.example.makersprojectbackend.service.forms.EnrollService;
import com.example.makersprojectbackend.service.impl.forms.EnrollServiceImpl;
import com.example.makersprojectbackend.service.impl.forms.FeedbackServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final CourseService courseService;
    private final CourseMapper courseMapper;
    private final EnrollServiceImpl enrollServiceImpl;
    private final FeedbackServiceImpl feedbackServiceImpl;
    private final EnrollService enrollService;
    private final UserService userService;
    private final UserMapper userMapper;


    @Value("${upload.folder}")
    String uploadPath;



    //USER
    @GetMapping("/user/get/{userId}")
    public UserDto getUserById(@PathVariable Long userId) {
        return userMapper.convertToDto(userService.getById(userId));
    }

    @GetMapping("/user/get/all")
    public List<UserDto> getAllUsers() {
        return userMapper.convertToDtoList(userService.getAll());
    }

    @DeleteMapping("/user/delete/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.delete(userId);
    }

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


    @GetMapping("/get/enrolls")
    public List<Enroll> getAllEnrolls() {
        return enrollService.getAll();
    }

    @GetMapping("/enrolls/download")
    public ResponseEntity<byte[]> downloadApplicationsList() {
        return enrollServiceImpl.exportToExcel(enrollServiceImpl.getAll());
    }

    @GetMapping("/get/feedbacks")
    public List<Feedback> getFeedbacks() {
        return feedbackServiceImpl.getAll();
    }

    @GetMapping("/feedbacks/download")
    public ResponseEntity<byte[]> downloadFeedbacksList() {
        return feedbackServiceImpl.exportToExcel(feedbackServiceImpl.getAll());
    }


    //  Бесплатные КУРСЫ
    @PostMapping("/course/free/create")
    public FreeCourseDto createFreeCourse(@RequestBody FreeCourseDto dto) {
        Course course = courseMapper.convertToEntity(dto);
        return courseMapper.convertToFreeCourseDto(courseService.create(course));
    }

    @PostMapping("/course/{id}")
    public ResponseEntity<?> uploadImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
        String uploadImage = courseService.uploadImage(id, file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @PutMapping("/course/free/update")
    public FreeCourseDto updateFreeCourse(@RequestBody FreeCourseDto dto) {
        Course course = courseMapper.convertToEntity(dto);
        return courseMapper.convertToFreeCourseDto(courseService.update(course));
    }

    // ПЛАТНЫЕ КУРСЫ
    @PostMapping("/course/paid/create")
    public PaidCourseDto createPaidCourse(@RequestBody PaidCourseDto dto) {
        Course course = courseMapper.convertToEntity(dto);
        return courseMapper.convertToPaidCourseDto(courseService.create(course));
    }

    @PutMapping("/course/paid/update")
    public PaidCourseDto updatePaidCourse(@RequestBody PaidCourseDto dto) {
        Course course = courseMapper.convertToEntity(dto);
        return courseMapper.convertToPaidCourseDto(courseService.update(course));
    }

    @DeleteMapping("/course/delete/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.delete(id);
    }


    @PutMapping("/course/lecture/add/{courseId}/{lectureId}")
    public FreeCourseDto addLectureToCourse(@PathVariable Long courseId, @PathVariable Long lectureId) {
        return courseMapper.convertToFreeCourseDto(courseService.addLecture(courseId, lectureId));
    }


    @DeleteMapping("/course/lecture/remove/{courseId}/{lectureId}")
    public FreeCourseDto removeLectureToCourse(@PathVariable Long courseId, @PathVariable Long lectureId) {
        return courseMapper.convertToFreeCourseDto(courseService.removeLecture(courseId, lectureId));
    }


    @PutMapping("/course/v/lecture/add/{courseId}/{lectureId}")
    public FreeCourseDto addVideoLectureToCourse(@PathVariable Long courseId, @PathVariable Long lectureId) {
        return courseMapper.convertToFreeCourseDto(courseService.addVideoLecture(courseId, lectureId));
    }


    @DeleteMapping("/course/v/lecture/remove/{courseId}/{lectureId}")
    public FreeCourseDto removeVideoLectureToCourse(@PathVariable Long courseId, @PathVariable Long lectureId) {
        return courseMapper.convertToFreeCourseDto(courseService.removeVideoLecture(courseId, lectureId));
    }
}
