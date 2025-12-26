package com.example.demo.service;

import com.example.demo.model.Progress;

public interface ProgressService {

    Progress saveOrUpdateProgress(Long userId, Long lessonId, Progress progress);

    Progress getProgressByUser(Long userId);

    // ✅ REQUIRED
    Progress getProgressForLesson(Long userId, Long lessonId);
}
