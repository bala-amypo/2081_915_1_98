package com.example.demo.service;

import com.example.demo.model.MicroLesson;
import java.util.List;

public interface MicroLessonService {
    MicroLesson createMicroLesson(MicroLesson microLesson);
    MicroLesson getMicroLesson(Long id);
    List<MicroLesson> getAllMicroLessons();
    MicroLesson updateMicroLesson(Long id, MicroLesson microLesson);
    void deleteMicroLesson(Long id);
}
