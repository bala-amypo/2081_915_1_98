package com.example.demo.service.impl;

import com.example.demo.model.MicroLesson;
import com.example.demo.repository.MicroLessonRepository;
import com.example.demo.service.MicroLessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MicroLessonServiceImpl implements MicroLessonService {

    @Autowired
    private MicroLessonRepository microLessonRepository;

    @Override
    public MicroLesson save(MicroLesson lesson) {
        return microLessonRepository.save(lesson);
    }

    @Override
    public List<MicroLesson> findByCourseId(Long courseId) {
        return microLessonRepository.findByCourseId(courseId);
    }
}
