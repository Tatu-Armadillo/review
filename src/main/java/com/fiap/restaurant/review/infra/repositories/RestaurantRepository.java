package com.fiap.restaurant.review.infra.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fiap.restaurant.review.infra.models.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query(" SELECT resturant FROM Restaurant resturant "
            + " WHERE LOWER(resturant.name) LIKE LOWER(CONCAT('%', :name, '%')) ")
    Page<Restaurant> findAllResturantsByName(Pageable pageable, @Param("name") String name);

}
