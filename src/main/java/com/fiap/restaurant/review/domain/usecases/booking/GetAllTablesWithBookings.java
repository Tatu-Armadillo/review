package com.fiap.restaurant.review.domain.usecases.booking;

import java.time.LocalDateTime;

import com.fiap.restaurant.review.domain.gateway.booking.GetAllTablesWithBookingsInterface;
import com.fiap.restaurant.review.domain.generic.output.OutputInterface;
import com.fiap.restaurant.review.domain.generic.output.OutputStatus;
import com.fiap.restaurant.review.domain.output.booking.GetAllTablesWithBookingsOutput;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class GetAllTablesWithBookings {
    
    private final GetAllTablesWithBookingsInterface getAllTablesWithBookingsInterface;
    private OutputInterface getAllTablesWithBookingsOutput;

    public void execute(String cnpj, LocalDateTime filterDay ){
        final var listRestaurantsEntities = this.getAllTablesWithBookingsInterface.getAllTablesWithBookingsAndResturant(cnpj, filterDay);
        this.getAllTablesWithBookingsOutput = new GetAllTablesWithBookingsOutput(listRestaurantsEntities,
        new OutputStatus(200,
         "OK",
         "List of Tables By bookings found!"));
    }


}
