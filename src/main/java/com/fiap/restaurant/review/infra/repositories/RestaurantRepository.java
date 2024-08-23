package com.fiap.restaurant.review.infra.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fiap.restaurant.review.infra.models.RestaurantModel;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantModel, Long> {

    @Query(" SELECT resturant FROM RestaurantModel resturant "
            + " WHERE LOWER(resturant.name) LIKE LOWER(CONCAT('%', :name, '%')) ")
    Page<RestaurantModel> findAllResturantsByName(Pageable pageable, @Param("name") String name);

}
