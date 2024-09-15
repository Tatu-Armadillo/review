package com.fiap.restaurant.review.infra.adapter.repository.restaurant;

import com.fiap.restaurant.review.domain.entities.restaurant.RestaurantEntity;
import com.fiap.restaurant.review.domain.gateway.restaurant.SaveRestaurantInterface;
import com.fiap.restaurant.review.infra.models.RestaurantModel;
import com.fiap.restaurant.review.infra.repositories.RestaurantRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SaveRestaurantRepository implements SaveRestaurantInterface{
    private final RestaurantRepository restaurantRepository;

    @Override
    public void saveRestaurant(RestaurantEntity restaurantEntity){
        RestaurantModel restaurantModel = new RestaurantModel();
        restaurantModel.setAlwaysOpen(restaurantEntity.getAlwaysOpen());
        restaurantModel.setCloseHour(restaurantEntity.getCloseHour());
        restaurantModel.setCnpj(restaurantEntity.getCnpj());
        restaurantModel.setFoodType(restaurantEntity.getFoodType());
        restaurantModel.setName(restaurantEntity.getName());
        restaurantModel.setOpenHour(restaurantEntity.getOpenHour());
        restaurantModel.setPhone(restaurantEntity.getPhone());
        restaurantModel.setTotalCapacity(restaurantEntity.getTotalCapacity());
        restaurantModel.setTotalGrade(restaurantEntity.getTotalGrade());
        // restaurantModel.setAddress(restaurantEntity.getAddress()); TODO: Fix address creation
        this.restaurantRepository.save(restaurantModel);
    }

}
