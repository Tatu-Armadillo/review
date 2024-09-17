package com.fiap.restaurant.review.domain.services.table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fiap.restaurant.review.domain.services.resturant.RestaurantService;
import com.fiap.restaurant.review.infra.models.TableModel;
import com.fiap.restaurant.review.infra.repositories.TableRepository;

@Service
public class TableService {

    private final TableRepository tableRepository;
    private final RestaurantService restaurantService;

    @Autowired
    public TableService(
            final TableRepository tableRepository,
            final RestaurantService restaurantService) {
        this.tableRepository = tableRepository;
        this.restaurantService = restaurantService;
    }

    public Page<TableModel> findAllTablesByResturant(final Pageable pageable, String cnpj) {
        return this.tableRepository.findAllTablesByResturant(pageable, cnpj);
    }

    public TableModel save(final TableModel entity) {
        final var resturant = this.restaurantService.findByCnpj(entity.getRestaurant().getCnpj());
        entity.setRestaurant(resturant);
        entity.setAvailable(true);
        return this.tableRepository.save(entity);
    }

}
