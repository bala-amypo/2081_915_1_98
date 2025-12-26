package com.example.demo.service.impl;

import com.example.demo.model.Progress;
import com.example.demo.repository.ProgressRepository;
import com.example.demo.service.ProgressService;
import org.springframework.stereotype.Service;

@Service
public class ProgressServiceImpl implements ProgressService {

    private final ProgressRepository progressRepository;

    public ProgressServiceImpl(ProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }

    @Override
    public Progress saveOrUpdateProgress(Long userId, Long lessonId, Progress progress) {
        return progressRepository.save(progress);
    }

    @Override
    public Progress getProgressByUser(Long userId) {
        return progressRepository.findTopByUserId(userId)
                .orElse(null);
    }

    // ✅ REQUIRED
    @Override
    public Progress getProgressForLesson(Long userId, Long lessonId) {
        return progressRepository.findByUserIdAndMicroLessonId(userId, lessonId)
                .orElse(null);
    }
}
