package com.example.demo.service;

import com.example.demo.model.MicroLesson;

import java.util.List;

public interface MicroLessonService {

    MicroLesson addLesson(Long courseId, MicroLesson lesson);

    MicroLesson updateLesson(Long lessonId, MicroLesson lesson);

    MicroLesson getLesson(Long lessonId);

    List<MicroLesson> findLessonsByFilters(
            String tags,
            String difficulty,
            String contentType
    );
}
