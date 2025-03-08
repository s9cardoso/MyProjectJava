package com.example.mySpringProject.service;

import com.example.mySpringProject.entities.DiningReview;
import com.example.mySpringProject.repositories.DiningReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.mySpringProject.entities.ReviewStatus;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiningReviewService {

    private final DiningReviewRepository diningReviewRepository;

    // Criar uma nova review
    public DiningReview submitReview(DiningReview review) {
        review.setStatus(ReviewStatus.PENDING); // Sempre começa como PENDING
        return diningReviewRepository.save(review);
    }

    // Buscar todas as reviews pendentes para aprovação
    public List<DiningReview> getPendingReviews() {
        return diningReviewRepository.findByStatus(ReviewStatus.PENDING);
    }

    // Aprovar ou rejeitar uma review
    public DiningReview updateReviewStatus(Long reviewId, ReviewStatus status) {
        Optional<DiningReview> optionalReview = diningReviewRepository.findById(reviewId);

        if (optionalReview.isPresent()) {
            DiningReview review = optionalReview.get();
            review.setStatus(status);
            return diningReviewRepository.save(review);
        } else {
            throw new RuntimeException("Review não encontrada");
        }
    }

    // Buscar todas as reviews aprovadas de um restaurante específico
    public List<DiningReview> getReviewsForRestaurant(Long restaurantId) {
        return diningReviewRepository.findByRestaurantId(restaurantId);
    }
}
