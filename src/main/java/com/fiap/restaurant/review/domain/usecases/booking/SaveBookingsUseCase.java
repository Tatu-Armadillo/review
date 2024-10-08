package com.fiap.restaurant.review.domain.usecases.booking;

import com.fiap.restaurant.review.domain.entities.booking.BookingsEntity;
import com.fiap.restaurant.review.domain.entities.restaurant.RestaurantEntity;
import com.fiap.restaurant.review.domain.entities.table.TableEntity;
import com.fiap.restaurant.review.domain.entities.user.UserEntity;
import com.fiap.restaurant.review.domain.gateway.booking.SaveBookingsInterface;
import com.fiap.restaurant.review.domain.generic.output.OutputInterface;
import com.fiap.restaurant.review.domain.generic.output.OutputStatus;
import com.fiap.restaurant.review.domain.input.booking.SaveBookingsInput;
import com.fiap.restaurant.review.domain.output.booking.SaveBookingsOutput;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class SaveBookingsUseCase {

    private final SaveBookingsInterface saveBookingsRepository;
    private OutputInterface saveBookingsOutput;

    public void execute(final SaveBookingsInput saveBookingInput) {

        final var bookingsEntity = new BookingsEntity(
                saveBookingInput.quantityPeople(),
                saveBookingInput.dateBook(),
                false,
                new UserEntity(null, saveBookingInput.cpfBook(), null, null, null, null),
                new TableEntity(null, null,
                        new RestaurantEntity(null, saveBookingInput.cnpjRestaurant(), null, null, null, null, null,
                                null, null, null)));

        this.saveBookingsRepository.saveBookings(bookingsEntity);
        this.saveBookingsOutput = new SaveBookingsOutput(bookingsEntity, new OutputStatus(
                201, "Created", "Bookings create with table and user"));
    }

}
