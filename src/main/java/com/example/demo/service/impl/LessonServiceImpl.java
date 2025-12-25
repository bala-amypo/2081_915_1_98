package com.example.demo.service.impl;

import com.example.demo.model.MicroLesson;
import com.example.demo.repository.MicroLessonRepository;
import com.example.demo.service.LessonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {

    private final MicroLessonRepository microLessonRepository;

    public LessonServiceImpl(MicroLessonRepository microLessonRepository) {
        this.microLessonRepository = microLessonRepository;
    }

    @Override
    public MicroLesson createLesson(MicroLesson lesson) {
        return microLessonRepository.save(lesson);
    }

    @Override
    public List<MicroLesson> getAllLessons() {
        return microLessonRepository.findAll();
    }

    @Override
    public MicroLesson getLessonById(Long id) {
        return microLessonRepository.findById(id).orElse(null);
    }

    @Override
    public List<MicroLesson> searchLessons(String title) {
        // ✅ FIXED: valid repository method
        return microLessonRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public void deleteLesson(Long id) {
        microLessonRepository.deleteById(id);
    }
}
