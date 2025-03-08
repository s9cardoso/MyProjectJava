package com.example.mySpringProject.controller;

import com.example.mySpringProject.entities.DiningReview;
import com.example.mySpringProject.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/pending-reviews")
    public ResponseEntity<List<DiningReview>> getPendingReviews() {
        return ResponseEntity.ok(adminService.getPendingReviews());
    }

    @PostMapping("/review/{reviewId}/action")
    public ResponseEntity<String> reviewAction(@PathVariable Long reviewId, @RequestParam boolean approve) {
        adminService.reviewAction(reviewId, approve);
        return ResponseEntity.ok("Review " + (approve ? "aprovada" : "rejeitada") + " com sucesso.");
    }
}


