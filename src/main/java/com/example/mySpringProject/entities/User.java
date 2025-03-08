package com.example.mySpringProject.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "DISPLAY_NAME", unique = true, nullable = false)
    private String displayName;
    
    @Column(name = "CITY")
    private String city;
    
    @Column(name = "STATE")
    private String state;
    
    @Column(name = "ZIPCODE")
    private String zipcode;
    
    @Column(name = "INTERESTED_IN_PEANUT_ALLERGY")
    private Boolean interestedInPeanutAllergy;
    
    @Column(name = "INTERESTED_IN_EGG_ALLERGY")
    private Boolean interestedInEggAllergy;
    
    @Column(name = "INTERESTED_IN_DAIRY_ALLERGY")
    private Boolean interestedInDairyAllergy;

    
}
