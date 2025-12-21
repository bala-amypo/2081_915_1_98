package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class MicroLesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lessonTitle;
    private int durationMinutes;

    @ManyToOne
    private Course course;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLessonTitle() { return lessonTitle; }
    public void setLessonTitle(String lessonTitle) { this.lessonTitle = lessonTitle; }

    public int getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(int durationMinutes) { this.durationMinutes = durationMinutes; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }
}
