package com.fiap.restaurant.review.infra.adapter.repository.booking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.fiap.restaurant.review.domain.entities.booking.BookingsEntity;
import com.fiap.restaurant.review.domain.entities.table.TableEntity;
import com.fiap.restaurant.review.domain.entities.user.UserEntity;
import com.fiap.restaurant.review.domain.gateway.booking.GetAllTablesWithBookingsInterface;
import com.fiap.restaurant.review.infra.repositories.BookingRepositoy;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetAllTablesWithBookingsRepository implements GetAllTablesWithBookingsInterface {

    private final BookingRepositoy bookingRepositoy;

    @Override
    public List<BookingsEntity> getAllTablesWithBookingsAndResturant(String cnpjResturant, LocalDate reservedDate) {

        final var models = this.bookingRepositoy.findAllBookingsWithTable(
            cnpjResturant, 
            LocalDateTime.of(reservedDate, LocalTime.of(0, 0, 1)),
            LocalDateTime.of(reservedDate, LocalTime.of(23, 59, 59)));
        List<BookingsEntity> entities = new ArrayList<>();
        models.forEach(model -> {
            entities.add(new BookingsEntity(
                    model.getQuantityPeople(),
                    model.getReservedDate(),
                    model.getCanceled(),
                    new UserEntity(
                            null,
                            model.getUser().getCpf(),
                            model.getUser().getPhone(),
                            null,
                            model.getUser().getFullName(),
                            null),
                    new TableEntity(
                            model.getTables().getCapacity(),
                            model.getTables().getAvailable(),
                            null)));
        });

        return entities;
    }

}
