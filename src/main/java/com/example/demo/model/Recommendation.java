package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "recommendations")
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    // CSV storage for lesson IDs (IMPORTANT for t42)
    @Column(nullable = false, length = 1000)
    private String lessonIdsCsv;

    @Column(nullable = false)
    private LocalDateTime generatedAt;

    @PrePersist
    public void prePersist() {
        if (generatedAt == null) {
            generatedAt = LocalDateTime.now();
        }
    }

    // ---------- Getters ----------

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getLessonIdsCsv() {
        return lessonIdsCsv;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    // ---------- Setters ----------

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setLessonIdsCsv(String lessonIdsCsv) {
        this.lessonIdsCsv = lessonIdsCsv;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }

    // ---------- CSV HELPERS (TEST CRITICAL) ----------

    public List<Long> parseLessonIds() {
        if (lessonIdsCsv == null || lessonIdsCsv.trim().isEmpty()) {
            return Collections.emptyList();
        }

        return Arrays.stream(lessonIdsCsv.split(","))
                .map(String::trim)
                .map(Long::valueOf)
                .collect(Collectors.toList());
    }

    public void setLessonIds(List<Long> lessonIds) {
        if (lessonIds == null || lessonIds.isEmpty()) {
            this.lessonIdsCsv = "";
        } else {
            this.lessonIdsCsv = lessonIds.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(","));
        }
    }

    // ---------- BUILDER (TEST REQUIRES THIS) ----------

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final Recommendation r = new Recommendation();

        public Builder userId(Long userId) {
            r.setUserId(userId);
            return this;
        }

        public Builder lessonIds(List<Long> lessonIds) {
            r.setLessonIds(lessonIds);
            return this;
        }

        public Builder generatedAt(LocalDateTime time) {
            r.setGeneratedAt(time);
            return this;
        }

        public Recommendation build() {
            return r;
        }
    }
}
