package com.example.demo.controller;

import com.example.demo.model.MicroLesson;
import com.example.demo.service.LessonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping("/{courseId}")
    public MicroLesson addLesson(
            @PathVariable Long courseId,
            @RequestBody MicroLesson lesson
    ) {
        return lessonService.addLesson(courseId, lesson);
    }

    @PutMapping("/{lessonId}")
    public MicroLesson updateLesson(
            @PathVariable Long lessonId,
            @RequestBody MicroLesson lesson
    ) {
        return lessonService.updateLesson(lessonId, lesson);
    }

    @GetMapping("/{lessonId}")
    public MicroLesson getLesson(@PathVariable Long lessonId) {
        return lessonService.getLesson(lessonId);
    }

    @GetMapping("/search")
    public List<MicroLesson> searchLessons(
            @RequestParam String difficulty,
            @RequestParam String contentType,
            @RequestParam String title
    ) {
        return lessonService.findLessonsByFilters(difficulty, contentType, title);
    }
}
