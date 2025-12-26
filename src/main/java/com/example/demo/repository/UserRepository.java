package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Used for login
    Optional<User> findByEmail(String email);

    // Used for registration duplicate check
    boolean existsByEmail(String email);
}
