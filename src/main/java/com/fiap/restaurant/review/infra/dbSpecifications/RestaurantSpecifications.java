package com.fiap.restaurant.review.infra.dbSpecifications;

import org.springframework.data.jpa.domain.Specification;

import com.fiap.restaurant.review.infra.models.RestaurantModel;

import jakarta.persistence.criteria.Join;

public class RestaurantSpecifications {
        public static Specification<RestaurantModel> hasName(String name) {
        return (root, query, cb) -> {
            if (name == null || name.isEmpty()) {
                return null;
            }
            return cb.equal(root.get("name"), name);
        };
    }

    public static Specification<RestaurantModel> hasCity(String city) {
        return (root, query, cb) -> {
            if (city == null || city.isEmpty()) {
                return null;
            }
            Join<Object, Object> addressJoin = root.join("address");
            return cb.equal(addressJoin.get("city"), city);
        };
    }

    public static Specification<RestaurantModel> hasFoodType(String foodType) {
        return (root, query, cb) -> {
            if (foodType == null || foodType.isEmpty()) {
                return null;
            }
            return cb.equal(root.get("foodType"), foodType);
        };
    }
}
