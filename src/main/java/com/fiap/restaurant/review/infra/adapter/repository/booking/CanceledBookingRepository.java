package com.fiap.restaurant.review.infra.adapter.repository.booking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fiap.restaurant.review.domain.entities.booking.BookingsEntity;
import com.fiap.restaurant.review.domain.entities.table.TableEntity;
import com.fiap.restaurant.review.domain.entities.user.UserEntity;
import com.fiap.restaurant.review.domain.gateway.booking.CanceledBookingInterface;
import com.fiap.restaurant.review.infra.models.BookingModel;
import com.fiap.restaurant.review.infra.repositories.BookingRepositoy;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CanceledBookingRepository implements CanceledBookingInterface {

        private final BookingRepositoy bookingRepositoy;

        @Override
        public BookingsEntity canceledBooking(String cpf, LocalDate reservedDate) {

                final var models = this.bookingRepositoy
                                .findBookingByUserCpfAndReservedDate(cpf,
                                                LocalDateTime.of(reservedDate, LocalTime.of(0, 0, 0)),
                                                LocalDateTime.of(reservedDate, LocalTime.of(23, 59, 59)));
                for (BookingModel m : models) {
                        m.setCanceled(true);
                        m.getTables().setAvailable(true);
                        return new BookingsEntity(
                                        m.getQuantityPeople(),
                                        m.getReservedDate(),
                                        m.getCanceled(),
                                        new UserEntity(
                                                        null,
                                                        m.getUser().getCpf(),
                                                        m.getUser().getPhone(),
                                                        null,
                                                        m.getUser().getFullName(),
                                                        null),
                                        new TableEntity(
                                                        m.getTables().getCapacity(),
                                                        m.getTables().getAvailable(),
                                                        null));
                }
                return null;
        }

}
