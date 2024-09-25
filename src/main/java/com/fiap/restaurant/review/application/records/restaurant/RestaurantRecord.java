package com.fiap.restaurant.review.application.records.restaurant;

import java.time.LocalTime;

public record RestaurantRecord(
        String name,
        String cnpj,
        String phone,
        String foodType,
        LocalTime openHour,
        LocalTime closeHour,
        Boolean alwaysOpen,
        Integer totalCapacity,
        Integer totalGrade,
        AddressRecord address) 
        {


}
