package com.fiap.restaurant.review.infra.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fiap.restaurant.review.infra.models.TableModel;

@Repository
public interface TableRepository extends JpaRepository<TableModel, Long> {

    @Query(" SELECT table FROM TableModel table "
            + " WHERE table.restaurant.cnpj = :cnpj ")
    List<TableModel> findAllTablesByResturant(String cnpj);
    
    Optional<TableModel> findFirstByRestaurantCnpjAndCapacityAndAvailable(
            String cnpjRestaurant,
            Integer capacity,
            boolean available);

}
