package com.fiap.restaurant.review.application.controllers.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.restaurant.review.application.controllers.mock.UserModelTestData;
import com.fiap.restaurant.review.infra.models.UserModel;
import com.fiap.restaurant.review.infra.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SaveUserController.class)
class SaveUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void shouldSaveUserSuccessfully() throws Exception {

        when(userRepository.save(any(UserModel.class))).thenReturn(UserModelTestData.createUser());

        mockMvc.perform(post("/user/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(UserModelTestData.createUser())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.cpf").value("12345678900"))
                .andExpect(jsonPath("$.phone").value("35991697607"))
                .andExpect(jsonPath("$.username").value("userName"))
                .andExpect(jsonPath("$.fullName").value("fullName"))
                .andExpect(jsonPath("$.password").value("password"));

        verify(userRepository).save(any(UserModel.class));
    }
}