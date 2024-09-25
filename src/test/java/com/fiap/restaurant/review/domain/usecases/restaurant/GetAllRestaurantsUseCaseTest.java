package com.fiap.restaurant.review.domain.usecases.restaurant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fiap.restaurant.review.domain.entities.restaurant.RestaurantEntity;
import com.fiap.restaurant.review.domain.gateway.restaurant.GetAllRestaurantsInterface;

public class GetAllRestaurantsUseCaseTest {

    @Mock
    private GetAllRestaurantsInterface getAllRestaurantsRepository;

    @InjectMocks
    private GetAllRestaurantsUseCase getAllRestaurantsUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        getAllRestaurantsUseCase = new GetAllRestaurantsUseCase(getAllRestaurantsRepository);
    }

    @Test
    void testExecute() {

        when(getAllRestaurantsRepository.getAllRestaurants()).thenReturn(List.of(new RestaurantEntity()));

        getAllRestaurantsUseCase.execute();

        verify(getAllRestaurantsRepository, times(1)).getAllRestaurants();

        final var output = getAllRestaurantsUseCase.getGetAllRestaurantOutput();
        assertEquals(200, output.getOutputStatus().getCode());
        assertEquals("OK", output.getOutputStatus().getCodeName());
        assertEquals("List of restaurants found!", output.getOutputStatus().getMessage());
        assertNotNull(output.getBody());
    }
}
