package com.fiap.restaurant.review.domain.output.booking;

import com.fiap.restaurant.review.domain.entities.booking.BookingsEntity;
import com.fiap.restaurant.review.domain.generic.output.OutputInterface;
import com.fiap.restaurant.review.domain.generic.output.OutputStatus;

import lombok.*;

@Data
@Getter
@Setter
public class SaveBookingsOutput implements OutputInterface {

    private final BookingsEntity bookingsEntity;
    private final OutputStatus outputStatus;

    public SaveBookingsOutput(
            final BookingsEntity bookingsEntity,
            final OutputStatus outputStatus) {
        this.bookingsEntity = bookingsEntity;
        this.outputStatus = outputStatus;
    }

    @Override
    public Object getBody() {
        return this.bookingsEntity;
    }

}
