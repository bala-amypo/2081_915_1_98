package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // ===== EXISTING =====
    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseService.createCourse(course); // ✔ correct
    }

    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable Long id, @RequestBody Course course) {
        return courseService.updateCourse(id, course);
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Long id) {
        return courseService.findById(id); // ✔ correct service method
    }

    // ===== ✅ REQUIRED ADDITION =====
    // GET /courses/instructor/{instructorId}

    @GetMapping("/instructor/{instructorId}")
    public List<Course> getCoursesByInstructor(@PathVariable Long instructorId) {
        return courseService.findByInstructor(instructorId); // ✔ matches service
    }
}
