package com.fiap.restaurant.review.application.controllers.review;

import org.springframework.http.ResponseEntity;

import com.fiap.restaurant.review.application.records.review.PostReviewRecord;
import com.fiap.restaurant.review.domain.generic.output.OutputInterface;
import com.fiap.restaurant.review.domain.input.review.PostReviewInput;
import com.fiap.restaurant.review.domain.usecases.review.PostReviewUseCase;
import com.fiap.restaurant.review.infra.adapter.repository.review.PostReviewRepository;
import com.fiap.restaurant.review.infra.configuration.web.response.ResponseBase;
import com.fiap.restaurant.review.infra.repositories.ReviewRepository;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review/post")
@Tag(name = "Review", description = "Endpoints for Managing Reviews")
public class PostReviewController {
    private final ReviewRepository reviewRepository;

    @PostMapping
    @Transactional
    @Operation(tags = {"Review"})
    public ResponseEntity<ResponseBase<Object>> postReview(@RequestBody final PostReviewRecord postReviewRecord){
        System.out.println("Grade: " + postReviewRecord.grade());
        OutputInterface outputInterface = this.getOutputInterface(postReviewRecord);
        return ResponseEntity.ok(ResponseBase.of(outputInterface.getBody()));

    }

    private OutputInterface getOutputInterface(PostReviewRecord postReviewRecord){
        PostReviewInput postReviewInput = new PostReviewInput(
            postReviewRecord.grade(),
            postReviewRecord.comment(),
            postReviewRecord.userId(),
            postReviewRecord.restaurantId()
            );
        PostReviewUseCase useCase = new PostReviewUseCase(new PostReviewRepository(reviewRepository));
        useCase.execute(postReviewInput);
        return useCase.getPostReviewOutput();
    }

}
