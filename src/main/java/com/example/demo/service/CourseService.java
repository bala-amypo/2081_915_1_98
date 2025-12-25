package com.example.demo.service;

import com.example.demo.model.Course;

import java.util.List;

public interface CourseService {

    Course save(Course course);

    List<Course> findAll();

    List<Course> findByInstructorId(Long instructorId);
}
