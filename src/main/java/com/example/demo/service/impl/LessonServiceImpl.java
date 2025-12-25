package com.example.demo.service.impl;

import com.example.demo.model.MicroLesson;
import com.example.demo.model.Course;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.MicroLessonRepository;
import com.example.demo.service.LessonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {

    private final MicroLessonRepository microLessonRepository;
    private final CourseRepository courseRepository;

    public LessonServiceImpl(
            MicroLessonRepository microLessonRepository,
            CourseRepository courseRepository
    ) {
        this.microLessonRepository = microLessonRepository;
        this.courseRepository = courseRepository;
    }

    // ==============================
    // ADD LESSON
    // ==============================
    @Override
    public MicroLesson addLesson(Long courseId, MicroLesson lesson) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        lesson.setCourse(course);
        return microLessonRepository.save(lesson);
    }

    // ==============================
    // UPDATE LESSON
    // ==============================
    @Override
    public MicroLesson updateLesson(Long lessonId, MicroLesson updated) {
        MicroLesson existing = microLessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        existing.setTitle(updated.getTitle());
        existing.setDifficulty(updated.getDifficulty());
        existing.setContentType(updated.getContentType());

        return microLessonRepository.save(existing);
    }

    // ==============================
    // GET LESSON BY ID
    // ==============================
    @Override
    public MicroLesson getLesson(Long lessonId) {
        return microLessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));
    }

    // ==============================
    // FIND LESSONS BY FILTERS
    // ==============================
    @Override
    public List<MicroLesson> findLessonsByFilters(
            String difficulty,
            String contentType,
            String title
    ) {
        return microLessonRepository.findByFilters(
                difficulty,
                contentType,
                title
        );
    }
}
