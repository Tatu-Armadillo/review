package com.fiap.restaurant.review.domain.usecases.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fiap.restaurant.review.domain.entities.booking.BookingsEntity;
import com.fiap.restaurant.review.domain.gateway.booking.GetAllTablesWithBookingsInterface;

public class GetAllTablesWithBookingsTest {

    @Mock
    private GetAllTablesWithBookingsInterface getAllTablesWithBookingsRepository;

    @InjectMocks
    private GetAllTablesWithBookings getAllTablesWithBookings;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        getAllTablesWithBookings = new GetAllTablesWithBookings(getAllTablesWithBookingsRepository);
    }

    @Test
    void testExecute() {

        when(getAllTablesWithBookingsRepository
                .getAllTablesWithBookingsAndResturant(anyString(), any(LocalDateTime.class)))
                .thenReturn(List.of(new BookingsEntity()));

        getAllTablesWithBookings.execute("", LocalDateTime.now());

        verify(getAllTablesWithBookingsRepository, times(1))
                .getAllTablesWithBookingsAndResturant(anyString(), any(LocalDateTime.class));

        final var output = getAllTablesWithBookings.getGetAllTablesWithBookingsOutput();
        assertEquals(200, output.getOutputStatus().getCode());
        assertEquals("OK", output.getOutputStatus().getCodeName());
        assertEquals("List of Tables By bookings found!", output.getOutputStatus().getMessage());
        assertNotNull(output.getBody());
    }
}
