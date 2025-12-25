package com.example.demo.repository;

import com.example.demo.model.MicroLesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MicroLessonRepository extends JpaRepository<MicroLesson, Long> {

    // Search lessons by title (case-insensitive)
    List<MicroLesson> findByTitleContainingIgnoreCase(String title);

}
