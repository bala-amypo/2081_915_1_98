package com.example.demo.controller;

import com.example.demo.model.Progress;
import com.example.demo.service.impl.ProgressServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progress")
public class ProgressController {

    private final ProgressServiceImpl progressService;

    public ProgressController(ProgressServiceImpl progressService) {
        this.progressService = progressService;
    }

    @PostMapping
    public Progress record(
            @RequestParam Long userId,
            @RequestParam Long lessonId,
            @RequestBody Progress progress
    ) {
        return progressService.recordProgress(userId, lessonId, progress);
    }

    @GetMapping("/{userId}")
    public List<Progress> list(@PathVariable Long userId) {
        return progressService.getUserProgress(userId);
    }
}
