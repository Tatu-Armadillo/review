package com.fiap.restaurant.review.infra.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "reviews")
@Data
public class ReviewModel {

    @Id
    @Column(name = "id_review")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "grade")
    private Integer grade;

    @Column(name = "comment")
    private String comment;

     // TODO: Fix typo on "users"
     @Column(name = "users")
     private Long userId;
 
     // Chave estrangeira do restaurante (sem o objeto RestaurantModel)
     @Column(name = "restaurant")
     private Long restaurantId;

}
