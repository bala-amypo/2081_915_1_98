package com.example.demo.service.impl;

import com.example.demo.model.MicroLesson;
import com.example.demo.service.MicroLessonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MicroLessonServiceImpl implements MicroLessonService {

    @Override
    public MicroLesson createMicroLesson(MicroLesson microLesson) {
        return null; // Implement DB logic later
    }

    @Override
    public MicroLesson getMicroLesson(Long id) {
        return null; // Implement DB logic later
    }

    @Override
    public List<MicroLesson> getAllMicroLessons() {
        return null; // Implement DB logic later
    }

    @Override
    public MicroLesson updateMicroLesson(Long id, MicroLesson microLesson) {
        return null; // Implement DB logic later
    }

    @Override
    public void deleteMicroLesson(Long id) {
        // Implement DB logic later
    }
}
