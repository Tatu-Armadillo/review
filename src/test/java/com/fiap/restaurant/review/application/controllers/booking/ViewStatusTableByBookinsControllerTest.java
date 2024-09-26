package com.fiap.restaurant.review.application.controllers.booking;

import com.fiap.restaurant.review.application.controllers.mock.BookingModelTestData;
import com.fiap.restaurant.review.domain.input.booking.BookingWithTableStatusFilter;
import com.fiap.restaurant.review.infra.models.BookingModel;
import com.fiap.restaurant.review.infra.repositories.BookingRepositoy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ViewStatusTableByBookinsControllerTest {

    @Mock
    private BookingRepositoy bookingRepositoy;

    @InjectMocks
    private ViewStatusTableByBookinsController viewStatusTableByBookinsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        viewStatusTableByBookinsController = new ViewStatusTableByBookinsController(bookingRepositoy);
    }

    @Test
    void showAllReviews() {
        List<BookingModel> bookingModelList = List.of(BookingModelTestData.createBooking());

        when(bookingRepositoy.findAllBookingsWithTable(anyString(), any(LocalDateTime.class)))
                .thenReturn(bookingModelList);

        var res = this.viewStatusTableByBookinsController.
                showAllReviews(new BookingWithTableStatusFilter("", LocalDate.now()));

        assertNotNull(res);

        verify(bookingRepositoy).findAllBookingsWithTable(anyString(), any(LocalDateTime.class));
    }
}