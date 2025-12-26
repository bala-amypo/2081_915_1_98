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

    // ---------------- EXISTING METHODS (DO NOT CHANGE) ----------------

    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    @PutMapping("/{courseId}")
    public Course updateCourse(
            @PathVariable Long courseId,
            @RequestBody Course course) {
        return courseService.updateCourse(courseId, course);
    }

    // ---------------- NEW METHODS (ADDED SAFELY) ----------------

    // ✅ 1. GET /courses/instructor/{instructorId}
    @GetMapping("/instructor/{instructorId}")
    public List<Course> getCoursesByInstructor(@PathVariable Long instructorId) {
        return courseService.getCoursesByInstructor(instructorId);
    }

    // ✅ 2. GET /courses/{courseId}
    @GetMapping("/{courseId}")
    public Course getCourseDetails(@PathVariable Long courseId) {
        return courseService.getCourse(courseId);
    }
}
