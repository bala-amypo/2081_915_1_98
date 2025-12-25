package com.example.demo.service.impl;

import com.example.demo.model.Course;
import com.example.demo.model.User;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.UserRepository;

public class CourseServiceImpl {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public CourseServiceImpl(
            CourseRepository courseRepository,
            UserRepository userRepository
    ) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    public Course createCourse(Course course, Long instructorId) {
        User instructor = userRepository.findById(instructorId)
                .orElseThrow(RuntimeException::new);

        if (courseRepository.existsByTitleAndInstructorId(
                course.getTitle(), instructorId)) {
            throw new RuntimeException();
        }

        course.setInstructor(instructor);
        return courseRepository.save(course);
    }

    public Course updateCourse(Long courseId, Course updated) {
        Course existing = courseRepository.findById(courseId)
                .orElseThrow(RuntimeException::new);

        existing.setTitle(updated.getTitle());
        existing.setDescription(updated.getDescription());
        return courseRepository.save(existing);
    }

    public Course getCourse(Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(RuntimeException::new);
    }
}
