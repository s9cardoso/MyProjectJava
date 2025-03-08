package com.example.mySpringProject.service;

import com.example.mySpringProject.entities.DiningReview;
import com.example.mySpringProject.entities.Restaurant;
import com.example.mySpringProject.entities.ReviewStatus;
import com.example.mySpringProject.repositories.DiningReviewRepository;
import com.example.mySpringProject.repositories.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final DiningReviewRepository diningReviewRepository;
    private final RestaurantRepository restaurantRepository;

    // Método para buscar todas as reviews pendentes
    public List<DiningReview> getPendingReviews() {
        return diningReviewRepository.findByStatus(ReviewStatus.PENDING);
    }

    // Método para aprovar ou rejeitar uma review
    @Transactional
    public void reviewAction(Long reviewId, boolean approve) {
        DiningReview review = diningReviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review não encontrada"));

        if (approve) {
            review.setStatus(ReviewStatus.APPROVED);
            diningReviewRepository.save(review);
            updateRestaurantScores(review.getRestaurantId()); // Atualiza a média do restaurante
        } else {
            review.setStatus(ReviewStatus.REJECTED);
            diningReviewRepository.save(review);
        }
    }

    // Método para atualizar a média das avaliações do restaurante
    private void updateRestaurantScores(Long restaurantId) {
        List<DiningReview> approvedReviews = diningReviewRepository.findByRestaurantId(restaurantId);

        if (approvedReviews.isEmpty()) {
            return; // Nenhuma review aprovada, não há como calcular a média
        }

        double peanutSum = 0, eggSum = 0, dairySum = 0;
        int peanutCount = 0, eggCount = 0, dairyCount = 0;

        for (DiningReview review : approvedReviews) {
            if (review.getPeanutScore() != null) {
                peanutSum += review.getPeanutScore();
                peanutCount++;
            }
            if (review.getEggScore() != null) {
                eggSum += review.getEggScore();
                eggCount++;
            }
            if (review.getDairyScore() != null) {
                dairySum += review.getDairyScore();
                dairyCount++;
            }
        }

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));

        restaurant.setPeanutScore(peanutCount > 0 ? peanutSum / peanutCount : null);
        restaurant.setEggScore(eggCount > 0 ? eggSum / eggCount : null);
        restaurant.setDairyScore(dairyCount > 0 ? dairySum / dairyCount : null);

        restaurantRepository.save(restaurant);
    }
}
