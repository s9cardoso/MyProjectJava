package com.example.mySpringProject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mySpringProject.entities.DiningReview;
import com.example.mySpringProject.service.DiningReviewService;
import jakarta.validation.Valid;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class DiningReviewController {

    private final DiningReviewService reviewService;

    @PostMapping
    public ResponseEntity<DiningReview> submitReview(@RequestBody @Valid DiningReview review) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.submitReview(review));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<DiningReview>> getReview(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getReviewsForRestaurant(id));
    }
}

