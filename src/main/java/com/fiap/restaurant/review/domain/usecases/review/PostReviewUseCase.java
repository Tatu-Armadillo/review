package com.fiap.restaurant.review.domain.usecases.review;

import com.fiap.restaurant.review.domain.entities.review.ReviewEntity;
import com.fiap.restaurant.review.domain.gateway.review.PostReviewInterface;
import com.fiap.restaurant.review.domain.generic.output.OutputInterface;
import com.fiap.restaurant.review.domain.generic.output.OutputStatus;
import com.fiap.restaurant.review.domain.input.review.PostReviewInput;
import com.fiap.restaurant.review.domain.output.review.PostReviewOutput;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostReviewUseCase {
    private final PostReviewInterface postReviewRepository;
    private OutputInterface postReviewOutput;

    public void execute(PostReviewInput postReviewInput){
        ReviewEntity reviewEntity = new ReviewEntity(
            postReviewInput.grade(),
            postReviewInput.comment(),
            postReviewInput.userId(),
            postReviewInput.restaurantId());
        
        this.postReviewRepository.postReview(reviewEntity);
        this.postReviewOutput = new PostReviewOutput(reviewEntity, new OutputStatus(
            201,
            "Created",
            "Review Created"));
    }

}
