package com.fiap.restaurant.review.infra.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
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
