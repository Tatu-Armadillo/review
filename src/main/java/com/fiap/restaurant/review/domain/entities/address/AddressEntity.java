package com.fiap.restaurant.review.domain.entities.address;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressEntity {
    private Long id;
    private String cep;
    private String publicPlace;
    private String complement;
    private String neighborhood;
    private String city;
    private String ufState;
    private Double latitude;
    private Double longitude;

    public AddressEntity(String cep, String publicPlace, String complement, String neighborhood, String city,
            String ufState, Double latitude, Double longitude) {
        this.cep = cep;
        this.publicPlace = publicPlace;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.city = city;
        this.ufState = ufState;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    
}
