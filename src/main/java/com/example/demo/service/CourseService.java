package com.example.demo.service;

import com.example.demo.model.Course;

import java.util.List;

public interface CourseService {

    Course createCourse(Course course, Long instructorId);

    Course updateCourse(Long courseId, Course course);

    Course getCourse(Long courseId);

    List<Course> getCoursesByInstructor(Long instructorId);

    List<Course> getAllCourses();   // 🔴 REQUIRED BY TESTS
}
