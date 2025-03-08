package com.example.mySpringProject.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.mySpringProject.entities.DiningReview;
import com.example.mySpringProject.entities.ReviewStatus;

import java.util.List;


@Repository
public interface DiningReviewRepository extends JpaRepository<DiningReview, Long> {
    List<DiningReview> findByStatus(ReviewStatus status); 
    List<DiningReview> findByRestaurantId(Long restaurantId); 
}
