package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users") // avoid SQL keyword conflict
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder   // ✅ REQUIRED by tests
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    private LocalDateTime createdAt;

    // ✅ REQUIRED by tests
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
