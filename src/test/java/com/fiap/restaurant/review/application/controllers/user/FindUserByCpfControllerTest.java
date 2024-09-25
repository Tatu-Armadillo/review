package com.fiap.restaurant.review.application.controllers.user;

import com.fiap.restaurant.review.application.controllers.mock.UserModelTestData;
import com.fiap.restaurant.review.infra.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FindUserByCpfController.class)
class FindUserByCpfControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Test
    void findUserByCpf() throws Exception {
        String cpf = "12345678900";

        when(userRepository.findUserByCpf(cpf)).thenReturn(Optional.of(UserModelTestData.createUser()));

        mockMvc.perform(get("/user/find?cpf=" + cpf)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cpf").value(cpf));
    }
}