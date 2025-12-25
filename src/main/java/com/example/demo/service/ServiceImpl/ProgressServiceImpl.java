package com.example.demo.service.impl;

import com.example.demo.model.MicroLesson;
import com.example.demo.model.Progress;
import com.example.demo.model.User;
import com.example.demo.repository.MicroLessonRepository;
import com.example.demo.repository.ProgressRepository;
import com.example.demo.repository.UserRepository;

import java.util.List;

public class ProgressServiceImpl {

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

    public Progress recordProgress(
            Long userId,
            Long lessonId,
            Progress input
    ) {
        User user = userRepository.findById(userId)
                .orElseThrow(RuntimeException::new);

        MicroLesson lesson = microLessonRepository.findById(lessonId)
                .orElseThrow(RuntimeException::new);

        Progress progress = progressRepository
                .findByUserIdAndMicroLessonId(userId, lessonId)
                .orElse(input);

        progress.setUser(user);
        progress.setMicroLesson(lesson);
        progress.setProgressPercent(input.getProgressPercent());
        progress.setStatus(input.getStatus());
        progress.setScore(input.getScore());

        return progressRepository.save(progress);
    }

    public List<Progress> getUserProgress(Long userId) {
        return progressRepository
                .findByUserIdOrderByLastAccessedAtDesc(userId);
    }
}
