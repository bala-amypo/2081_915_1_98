package com.example.demo.service.impl;

import com.example.demo.model.Progress;
import com.example.demo.model.User;
import com.example.demo.model.MicroLesson;
import com.example.demo.repository.ProgressRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.MicroLessonRepository;
import com.example.demo.service.ProgressService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProgressServiceImpl implements ProgressService {

    private final ProgressRepository progressRepository;
    private final UserRepository userRepository;
    private final MicroLessonRepository microLessonRepository;

    public ProgressServiceImpl(
            ProgressRepository progressRepository,
            UserRepository userRepository,
            MicroLessonRepository microLessonRepository
    ) {
        this.progressRepository = progressRepository;
        this.userRepository = userRepository;
        this.microLessonRepository = microLessonRepository;
    }

    @Override
    public Progress save(Progress progress) {
        return progressRepository.save(progress);
    }

    @Override
    public Progress recordProgress(Long userId, Long lessonId, Progress progress) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        MicroLesson lesson = microLessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        progress.setUser(user);
        progress.setMicroLesson(lesson);
        progress.setLastAccessedAt(LocalDateTime.now());

        return progressRepository.save(progress);
    }

    @Override
    public List<Progress> getUserProgress(Long userId) {
        return progressRepository.findByUserIdOrderByLastAccessedAtDesc(userId);
    }

    @Override
    public List<Progress> getLessonProgress(Long lessonId) {
        return progressRepository.findByMicroLessonId(lessonId);
    }
}
