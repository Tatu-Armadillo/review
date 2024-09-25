package com.fiap.restaurant.review.application.controllers.review;

import com.fiap.restaurant.review.application.records.review.PostReviewRecord;
import com.fiap.restaurant.review.domain.generic.output.OutputInterface;
import com.fiap.restaurant.review.domain.input.review.PostReviewInput;
import com.fiap.restaurant.review.domain.usecases.review.PostReviewUseCase;
import com.fiap.restaurant.review.infra.adapter.repository.review.PostReviewRepository;
import com.fiap.restaurant.review.infra.repositories.ReviewRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review/post")
@Tag(name = "Review", description = "Endpoints for Managing Reviews")
public class PostReviewController {
    private final ReviewRepository reviewRepository;

    @PostMapping
    @Transactional
    @Operation(tags = { "Review" })
    public ResponseEntity<Object> postReview(@RequestBody final PostReviewRecord postReviewRecord) {
        OutputInterface outputInterface = this.getOutputInterface(postReviewRecord);
        return ResponseEntity.ok(outputInterface.getBody());
    }

    private OutputInterface getOutputInterface(PostReviewRecord postReviewRecord) {
        PostReviewInput postReviewInput = new PostReviewInput(
                postReviewRecord.grade(),
                postReviewRecord.comment(),
                postReviewRecord.userId(),
                postReviewRecord.restaurantId());
        PostReviewUseCase useCase = new PostReviewUseCase(new PostReviewRepository(reviewRepository));
        useCase.execute(postReviewInput);
        return useCase.getPostReviewOutput();
    }

}
