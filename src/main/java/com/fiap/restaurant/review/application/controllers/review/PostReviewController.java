package com.fiap.restaurant.review.application.controllers.review;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.restaurant.review.infra.configuration.web.response.ResponseBase;
import com.fiap.restaurant.review.infra.repositories.ReviewRepository;

import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Post new Review", description = "Make review related to specific restaurant", tags = {
                        "Review" })
    public ResponseEntity<ResponseBase<Object>> postReview(){

    }

}
