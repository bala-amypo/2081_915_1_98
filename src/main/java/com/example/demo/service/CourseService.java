package com.example.demo.service;

import com.example.demo.model.Course;
import java.util.List;

public interface CourseService {

    Course createCourse(Course course);

    Course updateCourse(Long id, Course course);

    Course getCourseById(Long id);

    // ✅ REQUIRED
    List<Course> getCoursesByInstructor(Long instructorId);
}
