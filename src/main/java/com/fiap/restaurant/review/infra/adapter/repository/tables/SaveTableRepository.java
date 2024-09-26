package com.fiap.restaurant.review.infra.adapter.repository.tables;

import com.fiap.restaurant.review.domain.entities.table.TableEntity;
import com.fiap.restaurant.review.domain.gateway.tables.SaveTableInterface;
import com.fiap.restaurant.review.infra.models.TableModel;
import com.fiap.restaurant.review.infra.repositories.RestaurantRepository;
import com.fiap.restaurant.review.infra.repositories.TableRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SaveTableRepository implements SaveTableInterface {

    private final TableRepository tableRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public void saveTables(TableEntity tableEntity) {
        final var resturant = this.restaurantRepository
                .findResturantByCnpj(tableEntity.getRestaurant().getCnpj()).orElseThrow();

        final var model = new TableModel();
        model.setCapacity(tableEntity.getCapacity());
        model.setAvailable(tableEntity.getAvailable());
        model.setRestaurant(resturant);

        this.tableRepository.save(model);
    }

}
