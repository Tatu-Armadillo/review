package com.fiap.restaurant.review.application.records.restaurant;

public record AddressRecord(
        String cep,
        String publicPlace,
        String complement,
        String neighborhood,
        String city,
        String ufState,
        Double latitude,
        Double longitude) {

}