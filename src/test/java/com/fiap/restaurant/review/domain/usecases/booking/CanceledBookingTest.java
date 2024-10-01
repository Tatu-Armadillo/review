package com.fiap.restaurant.review.domain.usecases.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fiap.restaurant.review.domain.entities.booking.BookingsEntity;
import com.fiap.restaurant.review.domain.gateway.booking.CanceledBookingInterface;

public class CanceledBookingTest {

    @Mock
    private CanceledBookingInterface canceledBookingRepository;

    @InjectMocks
    private CanceledBookingUseCase canceledBooking;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        canceledBooking = new CanceledBookingUseCase(canceledBookingRepository);
    }

    @Test
    void testExecute() {

        when(canceledBookingRepository.canceledBooking(anyString(), any(LocalDate.class)))
                .thenReturn(new BookingsEntity());

        canceledBooking.execute("", LocalDate.now());

        final var output = canceledBooking.getCanceldBookingOutput();

        verify(canceledBookingRepository, times(1)).canceledBooking(anyString(), any(LocalDate.class));

        assertEquals(200, output.getOutputStatus().getCode());
        assertEquals("Update", output.getOutputStatus().getCodeName());
        assertEquals("Canceled Booking and Clear Table", output.getOutputStatus().getMessage());

    }

}
