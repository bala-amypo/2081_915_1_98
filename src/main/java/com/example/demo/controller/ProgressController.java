package com.example.demo.controller;

import com.example.demo.model.Progress;
import com.example.demo.service.ProgressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progress")
public class ProgressController {

    private final ProgressService service;

    public ProgressController(ProgressService service) {
        this.service = service;
    }

    @PostMapping
    public Progress save(@RequestBody Progress progress) {
        return service.saveProgress(progress);
    }

    @GetMapping
    public List<Progress> getAll() {
        return service.getAllProgress();
    }
}
