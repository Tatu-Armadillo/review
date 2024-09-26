package com.fiap.restaurant.review.application.controllers.booking;

import com.fiap.restaurant.review.application.controllers.mock.BookingModelTestData;
import com.fiap.restaurant.review.application.controllers.mock.TableModelTestData;
import com.fiap.restaurant.review.application.controllers.mock.UserModelTestData;
import com.fiap.restaurant.review.application.records.booking.BookingsRecord;
import com.fiap.restaurant.review.infra.models.BookingModel;
import com.fiap.restaurant.review.infra.repositories.BookingRepositoy;
import com.fiap.restaurant.review.infra.repositories.TableRepository;
import com.fiap.restaurant.review.infra.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SaveBookingsControllerTest {

    @Mock
    private BookingRepositoy bookingRepositoy;

    @Mock
    private TableRepository tableRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private SaveBookingsController saveBookingsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        saveBookingsController = new SaveBookingsController(bookingRepositoy, tableRepository, userRepository);
    }

    @Test
    void save() {
        when(userRepository.findUserByCpf(anyString())).thenReturn(Optional.of(UserModelTestData.createUser()));

        when(tableRepository.findFirstByRestaurantCnpjAndCapacityAndAvailable(anyString(), anyInt(), anyBoolean()))
                .thenReturn(Optional.of(TableModelTestData.createTable()));

        when(bookingRepositoy.save(any(BookingModel.class))).thenReturn(BookingModelTestData.createBooking());

        var res = this.saveBookingsController.save(new BookingsRecord("",
                "", 0, LocalDateTime.now()));

        assertNotNull(res);
        verify(bookingRepositoy).save(any(BookingModel.class));
    }
}