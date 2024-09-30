package com.fiap.restaurant.review.application.controllers.user;

import com.fiap.restaurant.review.application.controllers.mock.UserModelTestData;
import com.fiap.restaurant.review.infra.repositories.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class FindUserByCpfControllerTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private FindUserByCpfController findUserByCpfController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        findUserByCpfController = new FindUserByCpfController(userRepository);
    }

    @Test
    void findUserByCpf() throws Exception {
        when(userRepository.findUserByCpf(anyString())).thenReturn(Optional.of(UserModelTestData.createUser()));

        final var response = findUserByCpfController.findUserByCpf("");
        assertNotNull(response);
    }
}