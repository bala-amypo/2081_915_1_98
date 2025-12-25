package com.example.demo.controller;

import com.example.demo.model.Progress;
import com.example.demo.service.impl.ProgressServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/progress")
public class ProgressController {

    private final ProgressServiceImpl progressService;

    public ProgressController(ProgressServiceImpl progressService) {
        this.progressService = progressService;
    }

    @PostMapping("/{userId}/{lessonId}")
    public Progress recordProgress(
            @PathVariable Long userId,
            @PathVariable Long lessonId,
            @RequestBody Progress progress) {
        return progressService.recordProgress(userId, lessonId, progress);
    }

    @GetMapping("/{userId}")
    public List<Progress> getUserProgress(@PathVariable Long userId) {
        return progressService.getUserProgress(userId);
    }
}
