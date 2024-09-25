package com.fiap.restaurant.review.application.controllers.restaurant;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fiap.restaurant.review.application.controllers.mock.RestaurantModelTestData;
import com.fiap.restaurant.review.infra.models.RestaurantModel;
import com.fiap.restaurant.review.infra.repositories.AddressRepository;
import com.fiap.restaurant.review.infra.repositories.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SaveRestaurantController.class)
class SaveRestaurantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantRepository restaurantRepository;

    @MockBean
    private AddressRepository addressRepository;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void save() throws Exception {
        when(restaurantRepository.save(any(RestaurantModel.class))).thenReturn(RestaurantModelTestData.createRestaurant());

        mockMvc.perform(post("/restaurant/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(RestaurantModelTestData.createRestaurant())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("restaurant"))
                .andExpect(jsonPath("$.cnpj").value("92010209000154"))
                .andExpect(jsonPath("$.phone").value("35991697607"))
                .andExpect(jsonPath("$.foodType").value("comida"))
                .andExpect(jsonPath("$.alwaysOpen").value(true))
                .andExpect(jsonPath("$.totalCapacity").value(20))
                .andExpect(jsonPath("$.totalGrade").value(30))
                .andExpect(jsonPath("$.address.cep").value("37904246"));

        verify(restaurantRepository).save(any(RestaurantModel.class));
    }
}