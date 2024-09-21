package com.fiap.restaurant.review.infra.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fiap.restaurant.review.infra.models.TableModel;

@Repository
public interface TableRepository extends JpaRepository<TableModel, Long> {

    @Query(" SELECT table FROM TableModel table "
            + " WHERE table.restaurant.cnpj = :cnpj ")
    Page<TableModel> findAllTablesByResturant(Pageable pageable, String cnpj);

    // @Query(" SELECT table FROM TableModel table "
    //         + " WHERE table.restaurant.cnpj = :cnpjRestaurant "
    //         + " AND table.quantity = :quantity "
    //         + " AND table.available = :available "
    //         + " ORDER BY table.id ASC")
    Optional<TableModel> findFirstByRestaurantCnpjAndCapacityAndAvailable(
            String cnpjRestaurant,
            Integer capacity,
            boolean available);

}
