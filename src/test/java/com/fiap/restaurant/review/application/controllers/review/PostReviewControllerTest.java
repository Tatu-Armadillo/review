package com.fiap.restaurant.review.application.controllers.review;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.restaurant.review.application.controllers.mock.ReviewModelTestData;
import com.fiap.restaurant.review.infra.models.ReviewModel;
import com.fiap.restaurant.review.infra.repositories.ReviewRepository;
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

@WebMvcTest(PostReviewController.class)
class PostReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewRepository reviewRepository;

    @Test
    void postReview() throws Exception {
        when(reviewRepository.save(any(ReviewModel.class))).thenReturn(ReviewModelTestData.createReview());

        mockMvc.perform(post("/review/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(ReviewModelTestData.createReview())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Operation performed successfully"))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.comment").value("Excellent food and service!"))
                .andExpect(jsonPath("$.data.grade").value(5))
                .andExpect(jsonPath("$.data.userId").value(1))
                .andExpect(jsonPath("$.data.restaurantId").value(1));

        verify(reviewRepository).save(any(ReviewModel.class));
    }
}