package com.example.demo.controller;

import com.example.demo.model.MicroLesson;
import com.example.demo.service.ServiceImpl.LessonServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    private final LessonServiceImpl lessonService;

    public LessonController(LessonServiceImpl lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping
    public MicroLesson addLesson(
            @RequestParam Long courseId,
            @RequestBody MicroLesson lesson
    ) {
        return lessonService.addLesson(courseId, lesson);
    }

    @PutMapping("/{id}")
    public MicroLesson updateLesson(
            @PathVariable Long id,
            @RequestBody MicroLesson lesson
    ) {
        return lessonService.updateLesson(id, lesson);
    }

    @GetMapping("/{id}")
    public MicroLesson getLesson(@PathVariable Long id) {
        return lessonService.getLesson(id);
    }

    @GetMapping("/search")
    public List<MicroLesson> findLessons(
            @RequestParam(required = false) String tags,
            @RequestParam(required = false) String difficulty,
            @RequestParam(required = false) String contentType
    ) {
        return lessonService.findLessonsByFilters(tags, difficulty, contentType);
    }
}
