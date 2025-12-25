package com.example.demo.service;

import com.example.demo.model.MicroLesson;

import java.util.List;

public interface LessonService {

    MicroLesson createLesson(MicroLesson lesson);

    List<MicroLesson> getAllLessons();

    MicroLesson getLessonById(Long id);

    List<MicroLesson> searchLessons(String title);

    void deleteLesson(Long id);
}
