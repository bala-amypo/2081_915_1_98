package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private MicroLesson microLesson;

    private boolean completed;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public MicroLesson getMicroLesson() { return microLesson; }
    public void setMicroLesson(MicroLesson microLesson) {
        this.microLesson = microLesson;
    }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}
