package com.fiap.restaurant.review.domain.input.restaurant;

import java.time.LocalTime;


public record SaveRestaurantInput(
        String name,
        String cnpj,
        String phone,
        String foodType,
        LocalTime openHour,
        LocalTime closeHour,
        Boolean alwaysOpen,
        Integer totalCapacity,
        AddressRestaurantInput address) {


}
