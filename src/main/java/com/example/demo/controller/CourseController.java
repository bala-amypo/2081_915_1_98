package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.service.impl.CourseServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseServiceImpl courseService;

    public CourseController(CourseServiceImpl courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/{instructorId}")
    public Course createCourse(
            @PathVariable Long instructorId,
            @RequestBody Course course) {
        return courseService.createCourse(course, instructorId);
    }

    @PutMapping("/{courseId}")
    public Course updateCourse(
            @PathVariable Long courseId,
            @RequestBody Course course) {
        return courseService.updateCourse(courseId, course);
    }

    @GetMapping("/{courseId}")
    public Course getCourse(@PathVariable Long courseId) {
        return courseService.getCourse(courseId);
    }
}
