package com.example.demo.service.impl;

import com.example.demo.model.Progress;
import com.example.demo.repository.MicroLessonRepository;
import com.example.demo.repository.ProgressRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ProgressService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProgressServiceImpl implements ProgressService {

    private final ProgressRepository progressRepository;
    private final UserRepository userRepository;
    private final MicroLessonRepository microLessonRepository;

    // Constructor REQUIRED by tests
    public ProgressServiceImpl(ProgressRepository progressRepository,
                               UserRepository userRepository,
                               MicroLessonRepository microLessonRepository) {
        this.progressRepository = progressRepository;
        this.userRepository = userRepository;
        this.microLessonRepository = microLessonRepository;
    }

    // REQUIRED by DemoSystemTest
    public Progress recordProgress(long userId,
                                   long lessonId,
                                   Progress progress) {

        Progress existing = progressRepository
                .findByUserIdAndMicroLessonId(userId, lessonId)
                .orElse(null);

        if (existing != null) {
            existing.setProgressPercent(progress.getProgressPercent());
            existing.setStatus(progress.getStatus());
            existing.setScore(progress.getScore());
            existing.setLastAccessedAt(LocalDateTime.now());
            return progressRepository.save(existing);
        }

        progress.setUser(userRepository.findById(userId).orElseThrow());
        progress.setMicroLesson(
                microLessonRepository.findById(lessonId).orElseThrow()
        );
        progress.setLastAccessedAt(LocalDateTime.now());

        return progressRepository.save(progress);
    }

    // REQUIRED by interface
    @Override
    public List<Progress> getUserProgress(Long userId) {
        return progressRepository
                .findByUserIdOrderByLastAccessedAtDesc(userId);
    }

    // REQUIRED by interface
    @Override
    public List<Progress> getLessonProgress(Long lessonId) {
        return progressRepository.findByMicroLessonId(lessonId);
    }
}
