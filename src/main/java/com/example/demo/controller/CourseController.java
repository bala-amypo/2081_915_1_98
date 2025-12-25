package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.service.impl.CourseServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseServiceImpl courseService;

    public CourseController(CourseServiceImpl courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public Course createCourse(
            @RequestBody Course course,
            @RequestParam Long instructorId
    ) {
        return courseService.createCourse(course, instructorId);
    }

    @PutMapping("/{id}")
    public Course updateCourse(
            @PathVariable Long id,
            @RequestBody Course course
    ) {
        return courseService.updateCourse(id, course);
    }

    @GetMapping("/{id}")
    public Course getCourse(@PathVariable Long id) {
        return courseService.getCourse(id);
    }
}
