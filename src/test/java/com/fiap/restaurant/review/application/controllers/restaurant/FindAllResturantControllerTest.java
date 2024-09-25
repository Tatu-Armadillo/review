package com.fiap.restaurant.review.application.controllers.restaurant;

import com.fiap.restaurant.review.application.controllers.mock.RestaurantModelTestData;
import com.fiap.restaurant.review.infra.models.RestaurantModel;
import com.fiap.restaurant.review.infra.repositories.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FindAllResturantController.class)
class FindAllResturantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantRepository restaurantRepository;

    @Test
    void showAllResturants() throws Exception {
        List<RestaurantModel> restaurantList = List.of(RestaurantModelTestData.createRestaurant());

        when(restaurantRepository.findAll()).thenReturn(restaurantList);

        mockMvc.perform(get("/restaurant/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("restaurant"))
                .andExpect(jsonPath("$[0].address").value(RestaurantModelTestData.createRestaurant().getAddress()))
                .andExpect(jsonPath("$.length()").value(restaurantList.size()));


        verify(restaurantRepository, times(1)).findAll();
    }
}