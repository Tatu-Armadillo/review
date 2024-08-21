package com.fiap.restaurant.review.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "address")
@Data
public class Address {

    @Id
    @Column(name = "id_address")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cep")
    private String cep;

    @Column(name = "public_place")
    private String publicPlace;

    @Column(name = "complement")
    private String complement;

    @Column(name = "neighborhood")
    private String neighborhood;

    @Column(name = "city")
    private String city;

    @Column(name = "uf_state")
    private String ufState;

    @Column(name = "latitude_Y")
    private Double latitude;

    @Column(name = "longitude_X")
    private Double longitude;

}
