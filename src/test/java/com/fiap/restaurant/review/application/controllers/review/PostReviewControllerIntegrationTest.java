package com.fiap.restaurant.review.application.controllers.review;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.restaurant.review.application.records.review.PostReviewRecord;
import com.fiap.restaurant.review.infra.models.ReviewModel;
import com.fiap.restaurant.review.infra.repositories.ReviewRepository;
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

@WebMvcTest(PostReviewController.class)
class PostReviewControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewRepository reviewRepository;

    private PostReviewRecord postReviewRecord;

    @BeforeEach
    void setUp() {
        postReviewRecord = new PostReviewRecord(
                5,
                "Excellent food and service!",
                1L,
                1L
        );
    }

    @Test
    void postReview() throws Exception {
        ReviewModel savedReview = new ReviewModel();
        savedReview.setId(1L);
        savedReview.setGrade(5);
        savedReview.setComment("Excellent food and service!");
        savedReview.setUserId(1L);
        savedReview.setRestaurantId(1L);

        when(reviewRepository.save(any(ReviewModel.class))).thenReturn(savedReview);

        mockMvc.perform(post("/review/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(postReviewRecord)))
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