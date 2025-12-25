package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MicroLesson {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String difficulty;
    private String contentType;
    private Integer durationMinutes;
    private String tags;

    @ManyToOne
    private Course course;
}
