package com.fiap.restaurant.review.infra.adapter.repository.restaurant;

import java.util.ArrayList;
import java.util.List;

import com.fiap.restaurant.review.domain.entities.address.AddressEntity;
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
            AddressEntity addressEntity = new AddressEntity();
            addressEntity.setId(model.getAddress().getId());
            addressEntity.setCep(model.getAddress().getCep());
            addressEntity.setPublicPlace(model.getAddress().getPublicPlace());
            addressEntity.setComplement(model.getAddress().getComplement());
            addressEntity.setNeighborhood(model.getAddress().getNeighborhood());
            addressEntity.setCity(model.getAddress().getCity());
            addressEntity.setUfState(model.getAddress().getUfState());
            addressEntity.setLatitude(model.getAddress().getLatitude());
            addressEntity.setLongitude(model.getAddress().getLongitude());

            RestaurantEntity restaurantEntity = new RestaurantEntity();
            restaurantEntity.setAddress(addressEntity);
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
