package com.example.demo.service;

import com.example.demo.model.MicroLesson;

import java.util.List;

public interface MicroLessonService {

    MicroLesson save(MicroLesson lesson);

    List<MicroLesson> findByCourseId(Long courseId);
}
