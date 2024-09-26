package com.fiap.restaurant.review.domain.usecases.user;

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

import com.fiap.restaurant.review.domain.entities.user.UserEntity;
import com.fiap.restaurant.review.domain.gateway.user.SaveUserInterface;
import com.fiap.restaurant.review.domain.input.user.SaveUserInput;

public class SaveUserUseCaseTest {

    @Mock
    private SaveUserInterface saveUserRepository;

    @InjectMocks
    private SaveUserUseCase saveUserUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        saveUserUseCase = new SaveUserUseCase(saveUserRepository);
    }

    @Test
    void testExecute() {

        doNothing().when(saveUserRepository).saveUser(any(UserEntity.class));

        saveUserUseCase.execute(new SaveUserInput("12345678900", "", "", "", ""));

        verify(saveUserRepository, times(1)).saveUser(any(UserEntity.class));

        final var output = saveUserUseCase.getSaveUserOutput();
        assertEquals(201, output.getOutputStatus().getCode());
        assertEquals("Created", output.getOutputStatus().getCodeName());
        assertEquals("User created", output.getOutputStatus().getMessage());
        assertNotNull(output.getBody());

    }

    @Test
    void testExecuteFailed() {

        doNothing().when(saveUserRepository).saveUser(any(UserEntity.class));

        saveUserUseCase.execute(new SaveUserInput("", "", "", "", ""));

        final var output = saveUserUseCase.getSaveUserOutput();
        assertEquals(422, output.getOutputStatus().getCode());
        assertEquals("Unprocessable Entity", output.getOutputStatus().getCodeName());
        assertEquals("Invalid CPF: CPF must be 11 characters long.", output.getOutputStatus().getMessage());
        assertNotNull(output.getBody());

    }
}
