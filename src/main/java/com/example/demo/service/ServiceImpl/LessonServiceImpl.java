package com.example.demo.service.impl;

import com.example.demo.model.MicroLesson;
import com.example.demo.repository.MicroLessonRepository;
import com.example.demo.service.LessonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {

    private final MicroLessonRepository repository;

    public LessonServiceImpl(MicroLessonRepository repository) {
        this.repository = repository;
    }

    public MicroLesson saveLesson(MicroLesson lesson) {
        return repository.save(lesson);
    }

    public List<MicroLesson> getAllLessons() {
        return repository.findAll();
    }
}
