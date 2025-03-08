package com.example.mySpringProject.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "RESTAURANTS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "NAME")
    private String name;

    @Column(name = "ZIPCODE")
    private String zipCode;
    
    //@Column(name = "RESTAURANT_ID", nullable = false)
    //private Long restaurantId;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "PEANUT_SCORE")
    private Double peanutScore;
    
    @Column(name = "EGG_SCORE")
    private Double eggScore;
    
    @Column(name = "DAIRY_SCORE")
    private Double dairyScore;
    
    @Column(name = "OVERALL_SCORE")
    private Double overallScore;

    @Column(name = "COMMENTARY")
    private String commentary;

}