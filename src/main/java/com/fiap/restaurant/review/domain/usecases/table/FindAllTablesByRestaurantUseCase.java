package com.fiap.restaurant.review.domain.usecases.table;

import com.fiap.restaurant.review.domain.gateway.tables.FindAllTablesByRestaurantInterface;
import com.fiap.restaurant.review.domain.generic.output.OutputInterface;
import com.fiap.restaurant.review.domain.generic.output.OutputStatus;
import com.fiap.restaurant.review.domain.output.tables.FindAllTablesOutput;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class FindAllTablesByRestaurantUseCase {

    private final FindAllTablesByRestaurantInterface findAllTablesByRestaurantRepository;
    private OutputInterface findAllTablesOutput;

    public void execute(String cnpj) {
        final var enities = this.findAllTablesByRestaurantRepository.findAllTablesByRestaurant(cnpj);
        this.findAllTablesOutput = new FindAllTablesOutput(enities,
                new OutputStatus(200,
                        "OK",
                        "List of Tables By resturants found!"));
    }

}
