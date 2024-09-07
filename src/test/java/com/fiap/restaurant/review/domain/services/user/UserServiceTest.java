package com.fiap.restaurant.review.domain.services.user;

import com.fiap.restaurant.review.domain.exceptions.NotFoundException;
import com.fiap.restaurant.review.domain.services.UserService;
import com.fiap.restaurant.review.infra.models.UserModel;
import com.fiap.restaurant.review.infra.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private UserModel userModel;

    @BeforeEach
    void setUp() {
        userModel = new UserModel();
        userModel.setCpf("12345678900");
        userModel.setUsername("John Doe");
    }

    @Test
    void testFindUserByCpfSuccess() {
        when(userRepository.findUserByCpf(anyString())).thenReturn(Optional.of(userModel));

        UserModel foundUser = userService.findUserByCpf("12345678900");

        assertNotNull(foundUser);
        assertEquals("12345678900", foundUser.getCpf());
        assertEquals("John Doe", foundUser.getUsername());
        verify(userRepository, times(1)).findUserByCpf("12345678900");
    }

    @Test
    void testFindUserByCpfNotFound() {
        when(userRepository.findUserByCpf(anyString())).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> userService.findUserByCpf("12345678900"));
        assertEquals("m=findUserByCpf Not Found User with cpf = 12345678900", exception.getMessage());
        verify(userRepository, times(1)).findUserByCpf("12345678900");
    }

    @Test
    void testSaveUserAlreadyExists() {
        when(userRepository.findUserByCpf(anyString())).thenReturn(Optional.of(userModel));

        UserModel savedUser = userService.save(userModel);

        assertNotNull(savedUser);
        assertEquals("12345678900", savedUser.getCpf());
        verify(userRepository, never()).save(any(UserModel.class));
    }

    @Test
    void testSaveNewUser() {
        when(userRepository.findUserByCpf(anyString())).thenReturn(Optional.empty());
        when(userRepository.save(any(UserModel.class))).thenReturn(userModel);

        UserModel savedUser = userService.save(userModel);

        assertNotNull(savedUser);
        assertEquals("12345678900", savedUser.getCpf());
        assertEquals("John Doe", savedUser.getUsername());
        verify(userRepository, times(1)).save(userModel);
    }
}
