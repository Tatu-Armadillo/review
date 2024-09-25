package com.fiap.restaurant.review.domain.usecases.table;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.*;
import org.mockito.*;

import com.fiap.restaurant.review.domain.entities.booking.BookingsEntity;
import com.fiap.restaurant.review.domain.gateway.booking.SaveBookingsInterface;
import com.fiap.restaurant.review.domain.input.table.SaveBookingsInput;

public class SaveBookingsUseCaseTest {

    @Mock
    private SaveBookingsInterface saveBookingsRepository;

    @InjectMocks
    private SaveBookingsUseCase bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bookingService = new SaveBookingsUseCase(saveBookingsRepository);
    }

    @Test
    void testExecute() {
        final var saveBookingInput = new SaveBookingsInput(
                "12345678910110", "12345678900", 2, LocalDateTime.now());

        doNothing().when(saveBookingsRepository).saveBookings(any(BookingsEntity.class));

        bookingService.execute(saveBookingInput);

        verify(saveBookingsRepository, times(1)).saveBookings(any(BookingsEntity.class));
    }
}
