package com.fiap.restaurant.review.infra.adapter.repository.booking;

import com.fiap.restaurant.review.domain.entities.booking.BookingsEntity;
import com.fiap.restaurant.review.domain.exceptions.TablesNotAvailableException;
import com.fiap.restaurant.review.domain.gateway.booking.SaveBookingsInterface;
import com.fiap.restaurant.review.infra.models.BookingModel;
import com.fiap.restaurant.review.infra.repositories.BookingRepositoy;
import com.fiap.restaurant.review.infra.repositories.TableRepository;
import com.fiap.restaurant.review.infra.repositories.UserRepository;

public class SaveBookingRepository implements SaveBookingsInterface {

    private final BookingRepositoy bookingRepositoy;
    private final TableRepository tableRepository;
    private final UserRepository userRepository;

    public SaveBookingRepository(
            final BookingRepositoy bookingRepositoy,
            final TableRepository tableRepository,
            final UserRepository userRepository) {
        this.bookingRepositoy = bookingRepositoy;
        this.tableRepository = tableRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void saveBookings(final BookingsEntity bookingsEntity) {

        final var userModel = this.userRepository
                .findUserByCpf(bookingsEntity.getUserEntity().getCpf()).orElseThrow();

        final var tableModel = this.tableRepository
                .findFirstByRestaurantCnpjAndCapacityAndAvailable(
                        bookingsEntity.getTableEntity().getRestaurant().getCnpj(),
                        bookingsEntity.getQuantityPeople(),
                        true).orElseThrow(() -> new TablesNotAvailableException());
        tableModel.setAvailable(false);

        final var bookingModel = new BookingModel();
        bookingModel.setQuantityPeople(bookingsEntity.getQuantityPeople());
        bookingModel.setReservedDate(bookingsEntity.getReservedDate());
        bookingModel.setUser(userModel);
        bookingModel.setTables(tableModel);
        this.bookingRepositoy.save(bookingModel);
    }

}
