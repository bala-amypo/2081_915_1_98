package com.example.demo.repository;

import com.example.demo.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    // REQUIRED BY TESTS
    boolean existsByTitleAndInstructorId(String title, long instructorId);

    // REQUIRED BY SERVICES & CONTROLLER
    List<Course> findByInstructorId(Long instructorId);
}
