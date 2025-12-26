package com.example.demo.service;

import com.example.demo.model.Course;

import java.util.List;

public interface CourseService {

    // Create a new course
    Course createCourse(Course course);

    // Update an existing course
    Course updateCourse(Long courseId, Course course);

    // Get course details by courseId
    Course getCourse(Long courseId);

    // Get all courses for an instructor
    List<Course> getCoursesByInstructor(Long instructorId);
}
