package com.fiap.restaurant.review.application.controllers.user;

import com.fiap.restaurant.review.application.controllers.mock.UserModelTestData;
import com.fiap.restaurant.review.application.records.user.UserRecord;
import com.fiap.restaurant.review.infra.models.UserModel;
import com.fiap.restaurant.review.infra.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class SaveUserControllerTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private SaveUserController saveUserController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        saveUserController = new SaveUserController(userRepository);
    }

    @Test
    void shouldSaveUserSuccessfully() throws Exception {

        when(userRepository.save(any(UserModel.class))).thenReturn(UserModelTestData.createUser());

        final var response = this.saveUserController.save(new UserRecord("", "", "", "", ""));
        assertNotNull(response);

    }
}