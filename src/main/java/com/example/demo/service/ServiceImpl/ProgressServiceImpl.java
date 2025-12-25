package com.example.demo.service.ServiceImpl;

import com.example.demo.model.MicroLesson;
import com.example.demo.model.Progress;
import com.example.demo.model.User;
import com.example.demo.repository.MicroLessonRepository;
import com.example.demo.repository.ProgressRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ProgressService;
import org.springframework.stereotype.Service;

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
    public Progress recordProgress(Long userId, Long lessonId, Progress input) {
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

    @Override
    public List<Progress> getUserProgress(Long userId) {
        return progressRepository
                .findByUserIdOrderByLastAccessedAtDesc(userId);
    }
}
