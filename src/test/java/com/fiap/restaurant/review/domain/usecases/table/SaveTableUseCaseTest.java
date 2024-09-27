package com.fiap.restaurant.review.domain.usecases.table;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fiap.restaurant.review.domain.entities.table.TableEntity;
import com.fiap.restaurant.review.domain.gateway.tables.SaveTableInterface;
import com.fiap.restaurant.review.domain.input.table.SaveTablesInput;

public class SaveTableUseCaseTest {

    @Mock
    private SaveTableInterface saveTableRepository;

    @InjectMocks
    private SaveTableUseCase saveTableUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        saveTableUseCase = new SaveTableUseCase(saveTableRepository);
    }

    @Test
    void testExecute() {
        doNothing().when(saveTableRepository).saveTables(any(TableEntity.class));

        saveTableUseCase.execute(new SaveTablesInput(1, ""));

        verify(saveTableRepository, times(1)).saveTables(any(TableEntity.class));

        final var output = saveTableUseCase.getSaveBookingsOutput();
        assertEquals(201, output.getOutputStatus().getCode());
        assertEquals("Created", output.getOutputStatus().getCodeName());
        assertEquals("Tables Created", output.getOutputStatus().getMessage());
        assertNotNull(output.getBody());

    }
}
