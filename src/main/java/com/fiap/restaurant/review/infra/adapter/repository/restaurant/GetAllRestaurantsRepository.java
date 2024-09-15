package com.fiap.restaurant.review.infra.adapter.repository.restaurant;

import java.util.ArrayList;
import java.util.List;

import com.fiap.restaurant.review.domain.entities.restaurant.RestaurantEntity;
import com.fiap.restaurant.review.domain.gateway.restaurant.GetAllRestaurantsInterface;
import com.fiap.restaurant.review.infra.models.RestaurantModel;
import com.fiap.restaurant.review.infra.repositories.RestaurantRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetAllRestaurantsRepository implements GetAllRestaurantsInterface{
    private final RestaurantRepository restaurantRepository;

    @Override
    public List<RestaurantEntity> getAllRestaurants(){
        List<RestaurantModel> listRestaurantModels = this.restaurantRepository.findAll();
        List<RestaurantEntity> listRestaurantsEntities = new ArrayList<RestaurantEntity>();
        for (RestaurantModel model : listRestaurantModels) {
            RestaurantEntity restaurantEntity = new RestaurantEntity();
            // restaurantEntity.setAddress(model.getAddress()); TODO: add Address properly
            restaurantEntity.setAlwaysOpen(model.getAlwaysOpen());
            restaurantEntity.setCloseHour(model.getCloseHour());
            restaurantEntity.setCnpj(model.getCnpj());
            restaurantEntity.setFoodType(model.getFoodType());
            restaurantEntity.setId(model.getId());
            restaurantEntity.setName(model.getName());
            restaurantEntity.setOpenHour(model.getOpenHour());
            restaurantEntity.setPhone(model.getPhone());
            restaurantEntity.setTotalCapacity(model.getTotalCapacity());
            restaurantEntity.setTotalGrade(model.getTotalGrade());
            listRestaurantsEntities.add(restaurantEntity);
        }
        return listRestaurantsEntities;
    }


}
