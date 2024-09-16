package com.fiap.restaurant.review.infra.adapter.repository.restaurant;

import com.fiap.restaurant.review.domain.entities.address.AddressEntity;
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
