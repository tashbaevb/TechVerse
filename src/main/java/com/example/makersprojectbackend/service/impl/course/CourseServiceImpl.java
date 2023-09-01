package com.example.makersprojectbackend.service.impl.course;

import com.example.makersprojectbackend.entity.File;
import com.example.makersprojectbackend.entity.course.Course;
import com.example.makersprojectbackend.entity.course.Lecture;
import com.example.makersprojectbackend.entity.course.VideoLecture;
import com.example.makersprojectbackend.repository.FileRepository;
import com.example.makersprojectbackend.repository.course.CourseRepository;
import com.example.makersprojectbackend.repository.course.LectureRepository;
import com.example.makersprojectbackend.repository.course.VideoLectureRepository;
import com.example.makersprojectbackend.service.course.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final LectureRepository lectureRepository;
    private final VideoLectureRepository videoLectureRepository;
    private final FileRepository fileRepository;

    @Override
    public Course create(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course getById(Long id) {
        return courseRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course update(Course courseDetails) {
        Course course = getById(courseDetails.getId());
        course.setName(courseDetails.getName());
        course.setDescription(courseDetails.getDescription());
        course.setDuration(course.getDuration());
        course.setLectureQuantity(course.getLectures().size());
        return courseRepository.save(course);
    }

    @Override
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<Course> addLecture(Long courseId, Long lectureId){
        Course course = getById(courseId);
        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow();
        List<Lecture> lectures = course.getLectures();
        if (lectures.contains(lecture)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        lectures.add(lecture);
        lecture.setCourse(course);
        course.setLectures(lectures);
        course.setLectureQuantity(course.getLectures().size());
        lectureRepository.save(lecture);
        return ResponseEntity.ok(courseRepository.save(course));
    }

    @Override
    public Course removeLecture(Long courseId, Long lectureId) throws Exception {
        Course course = getById(courseId);
        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow();
        List<Lecture> lectures = course.getLectures();
        if (lectures.contains(lecture)){
            lectures.remove(lecture);
            lecture.setCourse(null);
            course.setLectures(lectures);
            course.setLectureQuantity(course.getLectures().size());
            lectureRepository.save(lecture);
            lectureRepository.delete(lecture);
            return courseRepository.save(course);
        }else throw new Exception("Лекция отсуствует в курсе");

    }

    @Override
    public Course addVideoLecture(Long courseId, Long videoLectureId) throws Exception {
        Course course = getById(courseId);
        VideoLecture videoLecture = videoLectureRepository.findById(videoLectureId).orElseThrow();
        List<VideoLecture> videoLectures = course.getVideoLectures();
        if (videoLectures.contains(videoLecture)){
            throw new Exception("Лекция уже есть");
        }
        videoLectures.add(videoLecture);
        videoLecture.setCourse(course);
        course.setVideoLectures(videoLectures);
        videoLectureRepository.save(videoLecture);
        return courseRepository.save(course);
    }

    @Override
    public Course removeVideoLecture(Long courseId, Long videoLectureId) throws Exception {
        Course course = getById(courseId);
        VideoLecture lecture = videoLectureRepository.findById(videoLectureId).orElseThrow();
        List<VideoLecture> videoLectures = course.getVideoLectures();
        if (videoLectures.contains(lecture)){
            videoLectures.remove(lecture);
            lecture.setCourse(null);
            course.setVideoLectures(videoLectures);
            videoLectureRepository.save(lecture);
            videoLectureRepository.delete(lecture);
            return courseRepository.save(course);
        }else throw new Exception("Лекция отсуствует в курсе");

    }

    @Override
    public String uploadImage(Long courseId, MultipartFile file) throws IOException {
        Course course = getById(courseId);

        File imageData = fileRepository.save(File.builder()
                .fileName(file.getOriginalFilename())
                .type(file.getContentType())
                .file(file.getBytes())
                .course(course)
                .build());
        course.setFile(imageData);
        courseRepository.save(course);
        if (imageData.getId() != null) {
            return "file uploaded successfully: " + file.getOriginalFilename();
        }
        return null;
    }


}
