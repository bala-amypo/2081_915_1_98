package com.example.demo.service.ServiceImpl;

import com.example.demo.model.Course;
import com.example.demo.model.MicroLesson;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.MicroLessonRepository;
import com.example.demo.service.MicroLessonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonServiceImpl implements MicroLessonService {

    private final MicroLessonRepository microLessonRepository;
    private final CourseRepository courseRepository;

    public LessonServiceImpl(
            MicroLessonRepository microLessonRepository,
            CourseRepository courseRepository
    ) {
        this.microLessonRepository = microLessonRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public MicroLesson addLesson(Long courseId, MicroLesson lesson) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(RuntimeException::new);

        lesson.setCourse(course);
        return microLessonRepository.save(lesson);
    }

    @Override
    public MicroLesson updateLesson(Long lessonId, MicroLesson updated) {
        MicroLesson existing = microLessonRepository.findById(lessonId)
                .orElseThrow(RuntimeException::new);

        existing.setTitle(updated.getTitle());
        existing.setDifficulty(updated.getDifficulty());
        existing.setContentType(updated.getContentType());
        return microLessonRepository.save(existing);
    }

    @Override
    public MicroLesson getLesson(Long lessonId) {
        return microLessonRepository.findById(lessonId)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public List<MicroLesson> findLessonsByFilters(
            String tags,
            String difficulty,
            String contentType
    ) {
        return microLessonRepository.findByFilters(
                tags, difficulty, contentType);
    }
}
