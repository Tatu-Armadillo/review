package com.fiap.restaurant.review.domain.input.restaurant;

public record AddressRestaurantInput(
    String cep,
    String publicPlace,
    String complement,
    String neighborhood,
    String city,
    String ufState,
    Double latitude,
    Double longitude)  {

}
