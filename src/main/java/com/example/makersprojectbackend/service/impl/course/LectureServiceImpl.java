package com.example.makersprojectbackend.service.impl.course;

import com.example.makersprojectbackend.entity.course.Lecture;
import com.example.makersprojectbackend.repository.course.LectureRepository;
import com.example.makersprojectbackend.service.course.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureServiceImpl implements LectureService {
    private final LectureRepository lectureRepository;

    @Override
    public Lecture create(Lecture lecture) {
        return lectureRepository.save(lecture);
    }

    @Override
    public Lecture getById(Long id) {
        return lectureRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Lecture> getAll() {
        return lectureRepository.findAll();
    }

    @Override
    public Lecture update(Lecture lecture) {

        return lectureRepository.save(lecture);
    }

    @Override
    public void delete(Long id) {
        lectureRepository.deleteById(id);
    }
}
