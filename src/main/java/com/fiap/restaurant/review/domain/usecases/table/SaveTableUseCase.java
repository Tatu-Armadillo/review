package com.fiap.restaurant.review.domain.usecases.table;

import com.fiap.restaurant.review.domain.entities.restaurant.RestaurantEntity;
import com.fiap.restaurant.review.domain.entities.table.TableEntity;
import com.fiap.restaurant.review.domain.gateway.tables.SaveTableInterface;
import com.fiap.restaurant.review.domain.generic.output.OutputInterface;
import com.fiap.restaurant.review.domain.generic.output.OutputStatus;
import com.fiap.restaurant.review.domain.input.table.SaveTablesInput;
import com.fiap.restaurant.review.domain.output.tables.SaveTablesOutput;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SaveTableUseCase {

    private final SaveTableInterface saveTableRepository;
    private OutputInterface saveBookingsOutput;

    public void execute(final SaveTablesInput input) {

        final var restaurant = new RestaurantEntity();
        restaurant.setCnpj(input.resturantCnpj());

        final var entity = new TableEntity(input.capacity(), true, restaurant);

        this.saveTableRepository.saveTables(entity);
        this.saveBookingsOutput = new SaveTablesOutput(entity, new OutputStatus(201, "Created", "Tables created"));
    }
}
