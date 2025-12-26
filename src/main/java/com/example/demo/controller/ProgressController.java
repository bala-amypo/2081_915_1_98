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

    // Existing service method
    @PostMapping
    public Progress save(@RequestBody Progress progress) {
        return progressService.save(progress);
    }

    @GetMapping("/user/{userId}")
    public List<Progress> byUser(@PathVariable Long userId) {
        return progressService.getAllProgress()
                .stream()
                .filter(p -> p.getUser().getId().equals(userId))
                .toList();
    }

    @GetMapping("/lesson/{lessonId}")
    public List<Progress> byLesson(@PathVariable Long lessonId) {
        return progressService.getAllProgress()
                .stream()
                .filter(p -> p.getMicroLesson().getId().equals(lessonId))
                .toList();
    }
}
