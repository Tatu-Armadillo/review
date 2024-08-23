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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "users", foreignKey = @ForeignKey(name = "fk_review_user"))
    private UserModel user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant", foreignKey = @ForeignKey(name = "fk_review_restaurant"))
    private RestaurantModel restaurant;

}
