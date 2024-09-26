package com.fiap.restaurant.review.domain.usecases.restaurant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fiap.restaurant.review.domain.entities.restaurant.RestaurantEntity;
import com.fiap.restaurant.review.domain.gateway.restaurant.SaveRestaurantInterface;
import com.fiap.restaurant.review.domain.input.restaurant.AddressRestaurantInput;
import com.fiap.restaurant.review.domain.input.restaurant.SaveRestaurantInput;

public class SaveRestaurantUseCaseTest {

    @Mock
    private SaveRestaurantInterface saveRestaurantRepository;

    @InjectMocks
    private SaveRestaurantUseCase saveRestaurantUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        saveRestaurantUseCase = new SaveRestaurantUseCase(saveRestaurantRepository);
    }

    @Test
    void testExecute() {

        doNothing().when(saveRestaurantRepository).saveRestaurant(any(RestaurantEntity.class));

        saveRestaurantUseCase.execute(
                new SaveRestaurantInput("", "", "", "", LocalTime.now(), LocalTime.now(), false, 1, 1,
                        new AddressRestaurantInput("", "", "", "", "", "", 0.0, 0.0)));

        verify(saveRestaurantRepository, times(1)).saveRestaurant(any(RestaurantEntity.class));

        final var output = saveRestaurantUseCase.getSaveRestaurantOutput();
        assertEquals(201, output.getOutputStatus().getCode());
        assertEquals("Created", output.getOutputStatus().getCodeName());
        assertEquals("Resturant created", output.getOutputStatus().getMessage());
        assertNotNull(output.getBody());
    }
}
