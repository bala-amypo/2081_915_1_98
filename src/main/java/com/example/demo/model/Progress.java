package com.example.demo.model;

public class Progress {
    private Long userId;
    private Long courseId;
    private int progressPercentage;

    public Progress() {}
    public Progress(Long userId, Long courseId, int progressPercentage) {
        this.userId = userId; this.courseId = courseId; this.progressPercentage = progressPercentage;
    }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }

    public int getProgressPercentage() { return progressPercentage; }
    public void setProgressPercentage(int progressPercentage) { this.progressPercentage = progressPercentage; }
}
