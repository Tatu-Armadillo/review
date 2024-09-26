package com.fiap.restaurant.review.domain.usecases.review;

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

import com.fiap.restaurant.review.domain.entities.review.ReviewEntity;
import com.fiap.restaurant.review.domain.gateway.review.PostReviewInterface;
import com.fiap.restaurant.review.domain.input.review.PostReviewInput;

public class PostReviewUseCaseTest {

    @Mock
    private PostReviewInterface postReviewRepository;

    @InjectMocks
    private PostReviewUseCase postReviewUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        postReviewUseCase = new PostReviewUseCase(postReviewRepository);
    }

    @Test
    void testExecute() {

        doNothing().when(postReviewRepository).postReview(any(ReviewEntity.class));

        postReviewUseCase.execute(new PostReviewInput(0, "", 0L, 0L));

        verify(postReviewRepository, times(1)).postReview(any(ReviewEntity.class));

        final var output = postReviewUseCase.getPostReviewOutput();
        assertEquals(201, output.getOutputStatus().getCode());
        assertEquals("Created", output.getOutputStatus().getCodeName());
        assertEquals("Review Created", output.getOutputStatus().getMessage());
        assertNotNull(output.getBody());

    }
}
