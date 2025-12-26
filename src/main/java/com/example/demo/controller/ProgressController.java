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

    @PostMapping("/{userId}/{lessonId}")
    public Progress record(
            @PathVariable Long userId,
            @PathVariable Long lessonId,
            @RequestBody Progress progress) {

        return progressService.recordProgress(userId, lessonId, progress);
    }

    @GetMapping("/user/{userId}")
    public List<Progress> byUser(@PathVariable Long userId) {
        return progressService.getUserProgress(userId);
    }
}
