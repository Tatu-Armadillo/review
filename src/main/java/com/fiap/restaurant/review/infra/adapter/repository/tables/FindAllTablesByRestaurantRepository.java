package com.fiap.restaurant.review.infra.adapter.repository.tables;

import java.util.List;

import com.fiap.restaurant.review.domain.entities.table.TableEntity;
import com.fiap.restaurant.review.domain.gateway.tables.FindAllTablesByRestaurantInterface;
import com.fiap.restaurant.review.infra.repositories.TableRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindAllTablesByRestaurantRepository implements FindAllTablesByRestaurantInterface {

    private final TableRepository tableRepository;

    @Override
    public List<TableEntity> findAllTablesByRestaurant(String cpnj) {

        return this.tableRepository.findAllTablesByResturant(cpnj)
                .stream()
                .map(m -> new TableEntity(m.getCapacity(), m.getAvailable(), null))
                .toList();

    }

}
