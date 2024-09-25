package com.fiap.restaurant.review.application.controllers.mock;

import com.fiap.restaurant.review.infra.models.UserModel;

public class UserModelTestData {

    public static UserModel createUser() {
       return new UserModel(null, "12345678900", "35991697607",
                "userName", "fullName", "password");
    }
}
