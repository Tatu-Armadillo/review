package com.fiap.restaurant.review.application.controllers.mock;

import com.fiap.restaurant.review.infra.models.AddressModel;

public class AddressModelTestData {

    public static AddressModel createAddress() {
        return new AddressModel(null, "37904246", "Rua das Flores", "Apto 101", "Centro",
                "São Paulo", "SP",  -23.55052, -46.63330);
    }
}
