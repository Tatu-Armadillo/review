package com.fiap.restaurant.review.infra.adapter.repository.review;

import com.fiap.restaurant.review.domain.entities.review.ReviewEntity;
import com.fiap.restaurant.review.domain.gateway.review.PostReviewInterface;
import com.fiap.restaurant.review.infra.models.ReviewModel;
import com.fiap.restaurant.review.infra.repositories.ReviewRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PostReviewRepository implements PostReviewInterface {

    private final ReviewRepository reviewRepository;

    public void postReview(ReviewEntity reviewEntity){
        ReviewModel reviewModel = new ReviewModel();
        reviewModel.setComment(reviewEntity.getComment());
        reviewModel.setCreateDate(reviewEntity.getCreateDate());
        reviewModel.setGrade(reviewEntity.getGrade());
        reviewModel.setId(reviewEntity.getId());
        reviewModel.setRestaurantId(reviewEntity.getRestaurantId());
        reviewModel.setUserId(reviewEntity.getUserId());
        this.reviewRepository.save(reviewModel);
        reviewEntity.setId(reviewModel.getId());
    }

}
