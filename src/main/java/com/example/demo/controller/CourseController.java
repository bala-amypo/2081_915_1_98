package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @PostMapping
    public Course add(@RequestBody Course course) {
        return service.saveCourse(course);
    }

    @GetMapping
    public List<Course> getAll() {
        return service.getAllCourses();
    }
}
