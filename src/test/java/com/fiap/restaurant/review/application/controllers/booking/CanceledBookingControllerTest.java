package com.fiap.restaurant.review.application.controllers.booking;

import com.fiap.restaurant.review.application.controllers.mock.BookingModelTestData;
import com.fiap.restaurant.review.domain.input.booking.CanceledBokingInput;
import com.fiap.restaurant.review.infra.repositories.BookingRepositoy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CanceledBookingControllerTest {

    @Mock
    private BookingRepositoy bookingRepositoy;

    @InjectMocks
    private CanceledBookingController canceledBookingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        canceledBookingController = new CanceledBookingController(bookingRepositoy);
    }

    @Test
    void canceledBookings() {
        when(bookingRepositoy.findBookingByUserCpfAndReservedDate(anyString(), any(LocalDateTime.class)))
                .thenReturn(Optional.of(BookingModelTestData.createBooking()));

        var res = this.canceledBookingController.canceledBookings(new CanceledBokingInput("", LocalDateTime.now()));

        assertNotNull(res);

        verify(bookingRepositoy).findBookingByUserCpfAndReservedDate(anyString(), any(LocalDateTime.class));
    }
}