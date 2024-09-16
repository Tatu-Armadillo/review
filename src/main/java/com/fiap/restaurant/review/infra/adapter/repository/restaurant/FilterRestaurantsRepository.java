package com.fiap.restaurant.review.infra.adapter.repository.restaurant;

import com.fiap.restaurant.review.domain.entities.restaurant.RestaurantEntity;
import com.fiap.restaurant.review.domain.gateway.restaurant.FilterRestaurantsInterface;
import com.fiap.restaurant.review.domain.input.restaurant.FilterRestaurantsInput;
import com.fiap.restaurant.review.infra.dbSpecifications.RestaurantSpecifications;
import com.fiap.restaurant.review.infra.models.RestaurantModel;
import com.fiap.restaurant.review.infra.repositories.RestaurantRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FilterRestaurantsRepository implements FilterRestaurantsInterface {
    private final RestaurantRepository restaurantRepository;

    public List<RestaurantEntity> filterRestaurants(FilterRestaurantsInput filterRestaurantsInput){
        Specification<RestaurantModel> spec = Specification.where(RestaurantSpecifications.hasName(filterRestaurantsInput.name()))
                                                           .and(RestaurantSpecifications.hasCity(filterRestaurantsInput.city()))
                                                           .and(RestaurantSpecifications.hasFoodType(filterRestaurantsInput.foodType()));
        List<RestaurantModel> listRestaurantModels = this.restaurantRepository.findAll(spec);
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
