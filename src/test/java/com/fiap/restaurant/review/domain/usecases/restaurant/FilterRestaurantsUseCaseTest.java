package com.fiap.restaurant.review.domain.usecases.restaurant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
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
import com.fiap.restaurant.review.domain.gateway.restaurant.FilterRestaurantsInterface;
import com.fiap.restaurant.review.domain.input.restaurant.FilterRestaurantsInput;

public class FilterRestaurantsUseCaseTest {

    @Mock
    private FilterRestaurantsInterface filterRestaurantsRepository;

    @InjectMocks
    private FilterRestaurantsUseCase filterRestaurantsUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        filterRestaurantsUseCase = new FilterRestaurantsUseCase(filterRestaurantsRepository);
    }

    @Test
    void testExecute() {

        when(filterRestaurantsRepository
                .filterRestaurants(any(FilterRestaurantsInput.class)))
                .thenReturn(List.of(new RestaurantEntity()));

        filterRestaurantsUseCase.execute(new FilterRestaurantsInput("", "", ""));

        verify(filterRestaurantsRepository, times(1))
                .filterRestaurants(any(FilterRestaurantsInput.class));

        final var output = filterRestaurantsUseCase.getFilterRestaurantOutput();
        assertEquals(200, output.getOutputStatus().getCode());
        assertEquals("OK", output.getOutputStatus().getCodeName());
        assertEquals("List of restaurants found!", output.getOutputStatus().getMessage());
        assertNotNull(output.getBody());
    }

}
