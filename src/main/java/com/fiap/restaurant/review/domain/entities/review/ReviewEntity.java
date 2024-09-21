package com.fiap.restaurant.review.domain.entities.review;


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
    private Long userId;
    private Long restaurantId;

    
    public ReviewEntity(Integer grade, String comment, Long userId, Long restaurantId) {
        this.grade = grade;
        this.comment = comment;
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.createDate = LocalDateTime.now();
    }

    



    
    
}
