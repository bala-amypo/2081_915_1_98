package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "recommendations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // User for whom recommendation is generated
    @Column(nullable = false)
    private Long userId;

    // Comma-separated lesson IDs (e.g. "1,2,3")
    @Column(length = 1000)
    private String recommendedLessonIds;

    // Confidence score for recommendation quality
    @Column(precision = 5, scale = 2)
    private BigDecimal confidenceScore;

    // Snapshot of logic / basis used for recommendation
    @Column(length = 2000)
    private String basisSnapshot;

    // When recommendation was generated
    private LocalDateTime generatedAt;

    /* -------------------------------------------------
       Helper methods (REQUIRED by services & tests)
       ------------------------------------------------- */

    /**
     * Converts CSV lesson IDs into List<Long>
     * Example: "1,2,3" -> [1, 2, 3]
     */
    public List<Long> parseRecommendationIds() {
        if (this.recommendedLessonIds == null || this.recommendedLessonIds.isBlank()) {
            return List.of();
        }

        return Arrays.stream(this.recommendedLessonIds.split(","))
                .map(String::trim)
                .map(Long::valueOf)
                .collect(Collectors.toList());
    }
}
