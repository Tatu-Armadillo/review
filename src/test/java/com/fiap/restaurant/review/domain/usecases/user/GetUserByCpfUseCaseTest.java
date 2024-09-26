package com.fiap.restaurant.review.domain.usecases.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fiap.restaurant.review.domain.entities.user.UserEntity;
import com.fiap.restaurant.review.domain.exceptions.NotFoundException;
import com.fiap.restaurant.review.domain.gateway.user.GetUserByCpfInterface;

public class GetUserByCpfUseCaseTest {

    @Mock
    private GetUserByCpfInterface getUserByCpfRepository;

    @InjectMocks
    private GetUserByCpfUseCase getUserByCpfUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        getUserByCpfUseCase = new GetUserByCpfUseCase(getUserByCpfRepository);
    }

    @Test
    void testExecuteSuccess() {

        when(getUserByCpfRepository.getUserByCpf(anyString())).thenReturn(
                new UserEntity(1L, "", "", "", "", ""));

        getUserByCpfUseCase.execute("");

        verify(getUserByCpfRepository, times(1)).getUserByCpf(anyString());

        final var output = getUserByCpfUseCase.getGetUserByCpfOutput();
        assertEquals(200, output.getOutputStatus().getCode());
        assertEquals("OK", output.getOutputStatus().getCodeName());
        assertEquals("User found!", output.getOutputStatus().getMessage());
        assertNotNull(output.getBody());
    }

    @Test
    void testExecuteFailed() {

        when(getUserByCpfRepository.getUserByCpf(anyString())).thenThrow(new NotFoundException(""));

        getUserByCpfUseCase.execute("");

        final var output = getUserByCpfUseCase.getGetUserByCpfOutput();
        assertEquals(404, output.getOutputStatus().getCode());
        assertEquals("Not Found", output.getOutputStatus().getCodeName());
        assertEquals("User Not Found", output.getOutputStatus().getMessage());
        assertNotNull(output.getBody());
    }
}
