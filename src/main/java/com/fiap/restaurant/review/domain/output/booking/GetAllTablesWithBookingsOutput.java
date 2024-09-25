package com.fiap.restaurant.review.domain.output.booking;

import java.util.List;

import com.fiap.restaurant.review.domain.entities.booking.BookingsEntity;
import com.fiap.restaurant.review.domain.generic.output.OutputInterface;
import com.fiap.restaurant.review.domain.generic.output.OutputStatus;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@RequiredArgsConstructor
public class GetAllTablesWithBookingsOutput implements OutputInterface {

    private List<BookingsEntity> bookings;
    private OutputStatus outputStatus;

    public GetAllTablesWithBookingsOutput(List<BookingsEntity> bookings, OutputStatus outputStatus) {
        this.bookings = bookings;
        this.outputStatus = outputStatus;
    }

    @Override
    public Object getBody() {
        return this.bookings;
    }

}
