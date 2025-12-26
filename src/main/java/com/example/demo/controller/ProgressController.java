package com.example.demo.controller;

import com.example.demo.model.Progress;
import com.example.demo.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/progress")
public class ProgressController {

    @Autowired
    private ProgressService progressService;

    // ===== EXISTING =====
    @PostMapping
    public Progress recordProgress(@RequestBody Progress progress) {
        return progressService.save(progress); // ✔ correct
    }

    @GetMapping("/user/{userId}")
    public List<Progress> getProgressByUser(@PathVariable Long userId) {
        return progressService.findByUser(userId); // ✔ correct
    }

    // ===== ✅ REQUIRED ADDITION =====
    // GET /progress/lesson/{lessonId}

    @GetMapping("/lesson/{lessonId}")
    public List<Progress> getProgressByLesson(@PathVariable Long lessonId) {
        return progressService.findByLesson(lessonId); // ✔ correct
    }
}
