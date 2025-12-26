package com.example.demo.controller;

import com.example.demo.model.Progress;
import com.example.demo.service.ProgressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/progress")
public class ProgressController {

    private final ProgressService progressService;

    public ProgressController(ProgressService progressService) {
        this.progressService = progressService;
    }

    @PostMapping
    public Progress record(@RequestBody Progress progress) {
        return progressService.recordProgress(progress);
    }

    @GetMapping("/user/{userId}")
    public List<Progress> getUserProgress(@PathVariable Long userId) {
        return progressService.getUserProgress(userId);
    }

    // ✔ REQUIRED ENDPOINT
    @GetMapping("/lesson/{lessonId}")
    public List<Progress> getLessonProgress(@PathVariable Long lessonId) {
        return progressService.getLessonProgress(lessonId);
    }
}
