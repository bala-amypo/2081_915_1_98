package com.example.demo.controller;

import com.example.demo.model.MicroLesson;
import com.example.demo.service.MicroLessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/micro-lessons")
public class MicroLessonController {

    @Autowired
    private MicroLessonService microLessonService;

    @PostMapping
    public ResponseEntity<MicroLesson> createMicroLesson(@RequestBody MicroLesson microLesson) {
        return ResponseEntity.ok(microLessonService.createMicroLesson(microLesson));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MicroLesson> getMicroLesson(@PathVariable Long id) {
        MicroLesson microLesson = microLessonService.getMicroLesson(id);
        if (microLesson == null) {
            // Removed ResourceNotFoundException import and use RuntimeException
            throw new RuntimeException("MicroLesson not found with id " + id);
        }
        return ResponseEntity.ok(microLesson);
    }

    @GetMapping
    public ResponseEntity<List<MicroLesson>> getAllMicroLessons() {
        return ResponseEntity.ok(microLessonService.getAllMicroLessons());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MicroLesson> updateMicroLesson(@PathVariable Long id, @RequestBody MicroLesson microLesson) {
        MicroLesson updated = microLessonService.updateMicroLesson(id, microLesson);
        if (updated == null) {
            throw new RuntimeException("MicroLesson not found with id " + id);
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMicroLesson(@PathVariable Long id) {
        microLessonService.deleteMicroLesson(id);
        return ResponseEntity.noContent().build();
    }
}
