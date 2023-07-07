package com.example.makersprojectbackend.service;

import com.example.makersprojectbackend.entity.test.Question;
import com.example.makersprojectbackend.entity.test.Test;
import com.example.makersprojectbackend.repository.TestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {
    private final TestRepository testRepository;

    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public Test create(Test test) {
        return testRepository.save(test);
    }

    public Test getById(Long id) {
        return testRepository.findById(id).orElseThrow();
    }

    public List<Test> getAll() {
        return testRepository.findAll();
    }

    public Test update(Test testDetails) {
        Test test = getById(testDetails.getId());
        test.setTitle(testDetails.getTitle());
        test.setQuestions(testDetails.getQuestions());
        test.setVideoLecture(testDetails.getVideoLecture());
        return testRepository.save(test);
    }

    public void delete(Long id) {
        testRepository.deleteById(id);
    }

    public Question start(Long testId) {
        Test test = getById(testId);
        return test.getQuestions().get(0);
    }
}
