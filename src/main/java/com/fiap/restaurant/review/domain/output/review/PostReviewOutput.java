package com.fiap.restaurant.review.domain.output.review;

import com.fiap.restaurant.review.domain.entities.review.ReviewEntity;
import com.fiap.restaurant.review.domain.generic.output.OutputInterface;
import com.fiap.restaurant.review.domain.generic.output.OutputStatus;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@RequiredArgsConstructor
public class PostReviewOutput implements OutputInterface {

    private ReviewEntity reviewEntity;
    private OutputStatus outputStatus;

    public PostReviewOutput(ReviewEntity reviewEntity, OutputStatus outputStatus) {
        this.reviewEntity = reviewEntity;
        this.outputStatus = outputStatus;
    }

    @Override
    public Object getBody() {
        return this.reviewEntity;
    }

}
