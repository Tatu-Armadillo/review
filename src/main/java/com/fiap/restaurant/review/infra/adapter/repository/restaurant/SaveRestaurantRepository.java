package com.fiap.restaurant.review.infra.adapter.repository.restaurant;

import com.fiap.restaurant.review.domain.entities.restaurant.RestaurantEntity;
import com.fiap.restaurant.review.domain.gateway.restaurant.SaveRestaurantInterface;
import com.fiap.restaurant.review.infra.models.AddressModel;
import com.fiap.restaurant.review.infra.models.RestaurantModel;
import com.fiap.restaurant.review.infra.repositories.AddressRepository;
import com.fiap.restaurant.review.infra.repositories.RestaurantRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SaveRestaurantRepository implements SaveRestaurantInterface{
    private final RestaurantRepository restaurantRepository;
    private final AddressRepository addressRepository;

    @Override
    public void saveRestaurant(RestaurantEntity restaurantEntity){
        AddressModel addressModel = new AddressModel();
        addressModel.setCep(restaurantEntity.getAddress().getCep());
        addressModel.setPublicPlace(restaurantEntity.getAddress().getPublicPlace());
        addressModel.setComplement(restaurantEntity.getAddress().getComplement());
        addressModel.setNeighborhood(restaurantEntity.getAddress().getNeighborhood());
        addressModel.setCity(restaurantEntity.getAddress().getCity());
        addressModel.setUfState(restaurantEntity.getAddress().getUfState());
        addressModel.setLatitude(restaurantEntity.getAddress().getLatitude());
        addressModel.setLongitude(restaurantEntity.getAddress().getLongitude());
        this.addressRepository.save(addressModel);

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
        restaurantModel.setAddress(addressModel);
        this.restaurantRepository.save(restaurantModel);
    }

}
