package com.example.mySpringProject.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "DINING_REVIEWS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class DiningReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "DISPLAY_NAME", nullable = false)
    private String displayName;
    
    @Column(name = "RESTAURANT_ID", nullable = false)
    private Long restaurantId;

    @Column(name = "PEANUT_SCORE")
    private Integer peanutScore;
    
    @Column(name = "EGG_SCORE")
    private Integer eggScore;
    
    @Column(name = "DAIRY_SCORE")
    private Integer dairyScore;
    
    @Column(name = "COMMENTARY")
    private String commentary;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private ReviewStatus status;
}


