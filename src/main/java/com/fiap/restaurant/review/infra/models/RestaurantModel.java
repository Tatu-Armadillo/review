package com.fiap.restaurant.review.infra.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Table(name = "restaurants")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantModel {

    @Id
    @Column(name = "id_restaurant")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    
    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "phone")
    private String phone;

    @Column(name = "food_type")
    private String foodType;

    @Column(name = "open_hour")
    private LocalTime openHour;

    @Column(name = "close_hour")
    private LocalTime closeHour;

    @Column(name = "always_open")
    private Boolean alwaysOpen;

    @Column(name = "total_capacity")
    private Integer totalCapacity;

    @Column(name = "total_grade")
    private Integer totalGrade;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address", foreignKey = @ForeignKey(name = "fk_restaurants_address"))
    private AddressModel address;

}
