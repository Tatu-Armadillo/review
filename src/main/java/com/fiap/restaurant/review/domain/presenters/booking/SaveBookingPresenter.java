package com.fiap.restaurant.review.domain.presenters.booking;

import java.util.HashMap;
import java.util.Map;

import com.fiap.restaurant.review.domain.generic.presenter.PresenterInterface;
import com.fiap.restaurant.review.domain.output.booking.SaveBookingsOutput;
import com.fiap.restaurant.review.domain.entities.booking.BookingsEntity;

public class SaveBookingPresenter implements PresenterInterface {
    private final SaveBookingsOutput saveBookingsOutput;

    public SaveBookingPresenter(SaveBookingsOutput saveBookingsOutput) {
        this.saveBookingsOutput = saveBookingsOutput;
    }

    public Map<String, Object> toArray() {
        Map<String, Object> array = new HashMap<>();
        BookingsEntity booking = this.saveBookingsOutput.getBookingsEntity();

        array.put("booking_id", booking.getId());
        array.put("quantity_people", booking.getQuantityPeople());
        array.put("reserved_date", booking.getReservedDate().toString());
        array.put("canceled", booking.isCanceled());

        Map<String, Object> userMap = new HashMap<>();
        userMap.put("full_name", booking.getUserEntity().getFullName());
        userMap.put("phone", booking.getUserEntity().getPhone());
        array.put("user", userMap);

        Map<String, Object> tableMap = new HashMap<>();
        tableMap.put("capacity", booking.getTableEntity().getCapacity());
        tableMap.put("available", booking.getTableEntity().getAvailable());
        array.put("table", tableMap);

        return array;
    }

    public SaveBookingsOutput getOutput() {
        return this.saveBookingsOutput;
    }
}
