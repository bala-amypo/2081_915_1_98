package com.example.demo.service.impl;

import com.example.demo.model.Course;
import com.example.demo.model.MicroLesson;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.MicroLessonRepository;

import java.util.List;

public class LessonServiceImpl {

    private final MicroLessonRepository microLessonRepository;
    private final CourseRepository courseRepository;

    public LessonServiceImpl(
            MicroLessonRepository microLessonRepository,
            CourseRepository courseRepository
    ) {
        this.microLessonRepository = microLessonRepository;
        this.courseRepository = courseRepository;
    }

    public MicroLesson addLesson(Long courseId, MicroLesson lesson) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(RuntimeException::new);

        lesson.setCourse(course);
        return microLessonRepository.save(lesson);
    }

    public MicroLesson updateLesson(Long lessonId, MicroLesson updated) {
        MicroLesson existing = microLessonRepository.findById(lessonId)
                .orElseThrow(RuntimeException::new);

        existing.setTitle(updated.getTitle());
        existing.setContentType(updated.getContentType());
        existing.setDifficulty(updated.getDifficulty());
        return microLessonRepository.save(existing);
    }

    public MicroLesson getLesson(Long lessonId) {
        return microLessonRepository.findById(lessonId)
                .orElseThrow(RuntimeException::new);
    }

    public List<MicroLesson> findLessonsByFilters(
            String tags,
            String difficulty,
            String contentType
    ) {
        return microLessonRepository.findByFilters(
                tags, difficulty, contentType);
    }
}
