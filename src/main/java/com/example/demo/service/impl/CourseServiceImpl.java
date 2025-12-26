package com.example.demo.service.impl;

import com.example.demo.model.Course;
import com.example.demo.model.User;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    // Constructor REQUIRED by tests
    public CourseServiceImpl(CourseRepository courseRepository,
                             UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    // REQUIRED by DemoSystemTest
    @Override
    public Course createCourse(Course course, long instructorId) {

        if (courseRepository.existsByTitleAndInstructorId(
                course.getTitle(), instructorId)) {
            throw new RuntimeException("Course already exists");
        }

        User instructor = userRepository.findById(instructorId)
                .orElseThrow(() -> new RuntimeException("Instructor not found"));

        course.setInstructor(instructor);
        return courseRepository.save(course);
    }

    @Override
    public Course getCourse(long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    @Override
    public List<Course> getCoursesByInstructor(long instructorId) {
        return courseRepository.findByInstructorId(instructorId);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}
