package com.fiap.restaurant.review.infra.adapter.repository.booking;

import java.time.LocalDateTime;

import com.fiap.restaurant.review.domain.entities.booking.BookingsEntity;
import com.fiap.restaurant.review.domain.entities.table.TableEntity;
import com.fiap.restaurant.review.domain.entities.user.UserEntity;
import com.fiap.restaurant.review.domain.gateway.booking.CanceledBookingInterface;
import com.fiap.restaurant.review.infra.repositories.BookingRepositoy;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CanceledBookingRepository implements CanceledBookingInterface {

    private final BookingRepositoy bookingRepositoy;

    @Override
    public BookingsEntity canceledBooking(String cpf, LocalDateTime reservedDate) {

        final var model = this.bookingRepositoy
                .findBookingByUserCpfAndReservedDate(cpf, reservedDate).orElseThrow();

        model.setCanceled(true);
        model.getTables().setAvailable(true);

        return new BookingsEntity(
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
                        null));
    }

}
