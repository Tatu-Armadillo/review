package com.fiap.restaurant.review.application.controllers.user;

import com.fasterxml.jackson.databind.ObjectMapper;
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
        UserModel userRecord = new UserModel();
        userRecord.setCpf("92700841132");
        userRecord.setPhone("65992346784");
        userRecord.setUsername("userName");
        userRecord.setFullName("fullName");
        userRecord.setPassword("password");

        when(userRepository.save(any(UserModel.class))).thenReturn(userRecord);

        mockMvc.perform(post("/user/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRecord)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Operation performed successfully"))
                .andExpect(jsonPath("$.data.cpf").value("92700841132"))
                .andExpect(jsonPath("$.data.phone").value("65992346784"))
                .andExpect(jsonPath("$.data.username").value("userName"))
                .andExpect(jsonPath("$.data.fullName").value("fullName"))
                .andExpect(jsonPath("$.data.password").value("password"));

        verify(userRepository).save(any(UserModel.class));
    }
}