package com.example.demo.controller;

import com.example.demo.model.MicroLesson;
import com.example.demo.service.LessonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    private final LessonService service;

    public LessonController(LessonService service) {
        this.service = service;
    }

    @PostMapping
    public MicroLesson add(@RequestBody MicroLesson lesson) {
        return service.saveLesson(lesson);
    }

    @GetMapping
    public List<MicroLesson> getAll() {
        return service.getAllLessons();
    }
}
