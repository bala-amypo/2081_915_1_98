// package com.example.demo.service.impl;

// import com.example.demo.model.Recommendation;
// import com.example.demo.repository.MicroLessonRepository;
// import com.example.demo.repository.RecommendationRepository;
// import com.example.demo.repository.UserRepository;
// import com.example.demo.service.RecommendationService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.time.LocalDateTime;
// import java.util.Collections;
// import java.util.List;
// import java.util.Optional;

// @Service
// public class RecommendationServiceImpl implements RecommendationService {

//     private final RecommendationRepository recommendationRepository;
//     private final UserRepository userRepository;
//     private final MicroLessonRepository microLessonRepository;

//     /**
//      * ✅ Constructor used by Spring Boot at runtime
//      */
//     @Autowired
//     public RecommendationServiceImpl(
//             RecommendationRepository recommendationRepository,
//             UserRepository userRepository,
//             MicroLessonRepository microLessonRepository) {

//         this.recommendationRepository = recommendationRepository;
//         this.userRepository = userRepository;
//         this.microLessonRepository = microLessonRepository;
//     }

//     /**
//      * ✅ Constructor REQUIRED by DemoSystemTest (DO NOT REMOVE)
//      */
//     public RecommendationServiceImpl(RecommendationRepository recommendationRepository) {
//         this.recommendationRepository = recommendationRepository;
//         this.userRepository = null;
//         this.microLessonRepository = null;
//     }

//     @Override
//     public List<Long> getLatestRecommendationIds(Long userId) {
//         Optional<Recommendation> latest =
//                 recommendationRepository
//                         .findByUserIdOrderByGeneratedAtDesc(userId)
//                         .stream()
//                         .findFirst();

//         return latest
//                 .map(Recommendation::parseRecommendationIds)
//                 .orElse(Collections.emptyList());
//     }

//     @Override
//     public List<Recommendation> getRecommendationsInRange(
//             Long userId,
//             LocalDateTime start,
//             LocalDateTime end) {

//         return recommendationRepository
//                 .findByUserIdAndGeneratedAtBetween(userId, start, end);
//     }

//     @Override
//     public Optional<Recommendation> getLatestRecommendation(Long userId) {
//         return recommendationRepository
//                 .findByUserIdOrderByGeneratedAtDesc(userId)
//                 .stream()
//                 .findFirst();
//     }
// }
