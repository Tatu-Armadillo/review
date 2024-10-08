package com.fiap.restaurant.review.domain.entities.restaurant;

import com.fiap.restaurant.review.domain.entities.address.AddressEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class RestaurantEntity {
    private Long id;
    private String name;
    private String cnpj;
    private String phone;
    private String foodType;
    private LocalTime openHour;
    private LocalTime closeHour;
    private Boolean alwaysOpen;
    private Integer totalCapacity;
    private Integer totalGrade;
    private AddressEntity address;

    public RestaurantEntity(String name, String cnpj, String phone, String foodType, LocalTime openHour,
            LocalTime closeHour, Boolean alwaysOpen, Integer totalCapacity, Integer totalGrade, AddressEntity address) {
        this.name = name;
        this.cnpj = cnpj;
        this.phone = phone;
        this.foodType = foodType;
        this.openHour = openHour;
        this.closeHour = closeHour;
        this.alwaysOpen = alwaysOpen;
        this.totalCapacity = totalCapacity;
        this.totalGrade = totalGrade;
        this.address = address;
    }

    public RestaurantEntity(Long id, String name, String cnpj, String phone, String foodType, LocalTime openHour,
            LocalTime closeHour, Boolean alwaysOpen, Integer totalCapacity) {
        this.id = id;
        this.name = name;
        this.cnpj = cnpj;
        this.phone = phone;
        this.foodType = foodType;
        this.openHour = openHour;
        this.closeHour = closeHour;
        this.alwaysOpen = alwaysOpen;
        this.totalCapacity = totalCapacity;
    }

    public RestaurantEntity(String name) {
        this.name = name;
    }





}
