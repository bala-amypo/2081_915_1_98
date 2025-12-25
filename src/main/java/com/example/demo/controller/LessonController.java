package com.example.demo.controller;

import com.example.demo.model.MicroLesson;
import com.example.demo.service.impl.LessonServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lessons")
public class LessonController {

    private final LessonServiceImpl lessonService;

    public LessonController(LessonServiceImpl lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping("/{courseId}")
    public MicroLesson addLesson(
            @PathVariable Long courseId,
            @RequestBody MicroLesson lesson) {
        return lessonService.addLesson(courseId, lesson);
    }

    @PutMapping("/{lessonId}")
    public MicroLesson updateLesson(
            @PathVariable Long lessonId,
            @RequestBody MicroLesson lesson) {
        return lessonService.updateLesson(lessonId, lesson);
    }

    @GetMapping("/{lessonId}")
    public MicroLesson getLesson(@PathVariable Long lessonId) {
        return lessonService.getLesson(lessonId);
    }

    @GetMapping("/search")
    public List<MicroLesson> findLessons(
            @RequestParam String tag,
            @RequestParam String difficulty,
            @RequestParam String contentType) {
        return lessonService.findLessonsByFilters(tag, difficulty, contentType);
    }
}
