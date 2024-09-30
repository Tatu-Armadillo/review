package com.fiap.restaurant.review.application.controllers.review;

import com.fiap.restaurant.review.application.controllers.mock.ReviewModelTestData;
import com.fiap.restaurant.review.application.records.review.PostReviewRecord;
import com.fiap.restaurant.review.infra.models.ReviewModel;
import com.fiap.restaurant.review.infra.repositories.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PostReviewControllerTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private PostReviewController postReviewController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        postReviewController = new PostReviewController(reviewRepository);
    }

    @Test
    void postReview() throws Exception {
        when(reviewRepository.save(any(ReviewModel.class))).thenReturn(ReviewModelTestData.createReview());

        final var response = postReviewController.postReview(new PostReviewRecord(0, "", 0L, 0L));
        assertNotNull(response);
    }
}