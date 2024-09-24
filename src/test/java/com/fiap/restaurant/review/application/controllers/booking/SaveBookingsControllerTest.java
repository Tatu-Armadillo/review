package com.fiap.restaurant.review.application.controllers.booking;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fiap.restaurant.review.application.records.booking.BookingsRecord;
import com.fiap.restaurant.review.infra.models.*;
import com.fiap.restaurant.review.infra.repositories.BookingRepositoy;
import com.fiap.restaurant.review.infra.repositories.TableRepository;
import com.fiap.restaurant.review.infra.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SaveBookingsController.class)
class SaveBookingsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingRepositoy bookingRepositoy;

    @MockBean
    private TableRepository tableRepository;

    @MockBean
    private UserRepository userRepository;

    private BookingModel bookingModel;

    private UserModel userModel;

    private TableModel tableModel;

    private RestaurantModel restaurantModel;

    private AddressModel addressModel;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());


        userModel = new UserModel(1L, "88888897787", "65889741254",
                "user", "name", "password");

        addressModel = new AddressModel(1L, "78095679", "Rua das Flores", "Apto 101",
                "Centro", "São Paulo", "SP", -23.55052, -46.63330);


        restaurantModel = new RestaurantModel(1L, "Restaurante Exemplo", "12345678000190",
                "(11) 98765-4321", "Comida Italiana", LocalTime.of(11, 0),
                LocalTime.of(23, 0), false, 100, 4, addressModel);

        tableModel = new TableModel(1L, 4, true, restaurantModel, new HashSet<>());

        bookingModel = new BookingModel(
                1L,
                5,
                LocalDateTime.now(),
                userModel,
                tableModel
        );
    }

    @Test
    void save() throws Exception {
        BookingsRecord bookingsRecord = new BookingsRecord("12345678000190", "88888897787", 4,
                LocalDateTime.of(2024, 9, 23, 19, 0));

        when(bookingRepositoy.save(any(BookingModel.class))).thenReturn(new BookingModel());

        mockMvc.perform(post("/booking/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookingsRecord)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }
}