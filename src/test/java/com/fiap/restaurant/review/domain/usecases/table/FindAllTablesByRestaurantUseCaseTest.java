package com.fiap.restaurant.review.domain.usecases.table;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fiap.restaurant.review.domain.entities.table.TableEntity;
import com.fiap.restaurant.review.domain.gateway.tables.FindAllTablesByRestaurantInterface;

public class FindAllTablesByRestaurantUseCaseTest {

    @Mock
    private FindAllTablesByRestaurantInterface findAllTablesByRestaurantRepository;

    @InjectMocks
    private FindAllTablesByRestaurantUseCase findAllTablesByRestaurantUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        findAllTablesByRestaurantUseCase = new FindAllTablesByRestaurantUseCase(findAllTablesByRestaurantRepository);
    }

    @Test
    void testExecute() {

        when(findAllTablesByRestaurantRepository
                .findAllTablesByRestaurant(anyString()))
                .thenReturn(List.of(new TableEntity()));

        findAllTablesByRestaurantUseCase.execute("");

        verify(findAllTablesByRestaurantRepository, times(1))
                .findAllTablesByRestaurant(anyString());

        final var output = findAllTablesByRestaurantUseCase.getFindAllTablesOutput();
        assertEquals(200, output.getOutputStatus().getCode());
        assertEquals("OK", output.getOutputStatus().getCodeName());
        assertEquals("List of Tables By resturants found!", output.getOutputStatus().getMessage());
        assertNotNull(output.getBody());

    }
}
