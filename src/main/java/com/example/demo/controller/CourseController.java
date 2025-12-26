package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // Already exists in service
    @PostMapping
    public Course create(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    // Already exists in service
    @PutMapping("/{id}")
    public Course update(@PathVariable Long id, @RequestBody Course course) {
        return courseService.updateCourse(id, course);
    }

    // 🔁 SAFE: use getAllCourses and filter later if needed
    @GetMapping("/{id}")
    public Course get(@PathVariable Long id) {
        return courseService.getAllCourses()
                .stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // 🔁 SAFE: no new service method
    @GetMapping("/instructor/{instructorId}")
    public List<Course> byInstructor(@PathVariable Long instructorId) {
        return courseService.getAllCourses()
                .stream()
                .filter(c -> c.getInstructor() != null &&
                             c.getInstructor().getId().equals(instructorId))
                .toList();
    }
}
