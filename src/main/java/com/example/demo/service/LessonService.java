package com.example.demo.service;

import com.example.demo.model.MicroLesson;
import java.util.List;

public interface LessonService {

    MicroLesson saveLesson(MicroLesson lesson);

    List<MicroLesson> getAllLessons();
}
