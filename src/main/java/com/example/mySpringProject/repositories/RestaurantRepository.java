package com.example.mySpringProject.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.mySpringProject.entities.Restaurant;
import java.util.List;

//import com.example.mySpringProject.entities.AllergyType;


@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    boolean existsByNameAndZipCode(String name, String zipCode);

    List<Restaurant> findByZipCode(String zipCode);

}
