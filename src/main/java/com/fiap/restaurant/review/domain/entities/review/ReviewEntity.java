package com.fiap.restaurant.review.domain.entities.review;

import com.fiap.restaurant.review.infra.models.RestaurantModel;
import com.fiap.restaurant.review.infra.models.UserModel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ReviewEntity {
    
    private Long id;
    private LocalDateTime createDate;
    private Integer grade;
    private String comment;
    private UserModel user;
    private RestaurantModel restaurant;

    public ReviewEntity(Integer grade, String comment, UserModel user, RestaurantModel restaurant) {
        this.grade = grade;
        this.comment = comment;
        this.user = user;
        this.restaurant = restaurant;
        this.createDate = LocalDateTime.now();
    }

    
}
