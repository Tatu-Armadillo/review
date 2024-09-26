package com.fiap.restaurant.review.application.controllers.restaurant;

import com.fiap.restaurant.review.application.controllers.mock.RestaurantModelTestData;
import com.fiap.restaurant.review.infra.dbSpecifications.RestaurantSpecifications;
import com.fiap.restaurant.review.infra.models.RestaurantModel;
import com.fiap.restaurant.review.infra.repositories.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FilterRestaurantsController.class)
class FilterRestaurantsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantRepository restaurantRepository;

    @Test
    void filterResturants() throws Exception {
        String name = "restaurant";
        String city = "city";
        String foodType = "foodType";

        List<RestaurantModel> restaurantList = List.of(RestaurantModelTestData.createRestaurant());

        Specification<RestaurantModel> spec = Specification.where(RestaurantSpecifications.hasName(name))
                .and(RestaurantSpecifications.hasCity(city))
                .and(RestaurantSpecifications.hasFoodType(foodType));

        when(restaurantRepository.findAll(spec)).thenReturn(restaurantList);

        mockMvc.perform(get("/restaurant/filter?name=" + name + "&city=" + city + "&foodType=" + foodType))
                .andExpect(status().isOk());

    }
}