package com.example.makersprojectbackend.service.impl.course;

import com.example.makersprojectbackend.entity.course.VideoLecture;
import com.example.makersprojectbackend.repository.course.VideoLectureRepository;
import com.example.makersprojectbackend.service.course.VideoLectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoLectureServiceImpl implements VideoLectureService {
    private final VideoLectureRepository videoLectureRepository;

    @Override
    public VideoLecture create(VideoLecture videoLecture) {
        return videoLectureRepository.save(videoLecture);
    }

    @Override
    public VideoLecture getById(Long id) {
        return videoLectureRepository.findById(id).orElseThrow();
    }

    @Override
    public List<VideoLecture> getAll() {
        return videoLectureRepository.findAll();
    }

    @Override
    public VideoLecture update(VideoLecture videoLecture) {

        return videoLectureRepository.save(videoLecture);
    }

    @Override
    public void delete(Long id) {
        videoLectureRepository.deleteById(id);
    }
}
