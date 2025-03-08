package com.example.mySpringProject.service;

import com.example.mySpringProject.entities.Restaurant;
//import com.example.mySpringProject.entities.AllergyType;
import com.example.mySpringProject.repositories.RestaurantRepository;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    // Criar um novo restaurante
    public Restaurant createRestaurant(Restaurant restaurant) {
        boolean exists = restaurantRepository.existsByNameAndZipCode(restaurant.getName(), restaurant.getZipCode());
        if (exists) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Restaurant with the same name and zip code already exists");
        }
        return restaurantRepository.save(restaurant);
    }

    // Buscar restaurante pelo ID
    public Restaurant getRestaurant(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found"));
    }

    // Buscar restaurantes por zipCode e alergia
    public List<Restaurant> searchRestaurants(String zipCode) {
        return restaurantRepository.findByZipCode(zipCode);
    }
    
}
