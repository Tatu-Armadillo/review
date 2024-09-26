package com.fiap.restaurant.review.domain.usecases.restaurant;

import com.fiap.restaurant.review.domain.entities.address.AddressEntity;
import com.fiap.restaurant.review.domain.entities.restaurant.RestaurantEntity;
import com.fiap.restaurant.review.domain.gateway.restaurant.SaveRestaurantInterface;
import com.fiap.restaurant.review.domain.generic.output.OutputInterface;
import com.fiap.restaurant.review.domain.generic.output.OutputStatus;
import com.fiap.restaurant.review.domain.input.restaurant.SaveRestaurantInput;
import com.fiap.restaurant.review.domain.output.restaurant.SaveRestaurantOutput;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class SaveRestaurantUseCase {
    private final SaveRestaurantInterface saveRestaurantRepository;
    private OutputInterface saveRestaurantOutput;

    public void execute(SaveRestaurantInput saveRestaurantInput) {
        RestaurantEntity restaurantEntity = new RestaurantEntity(
            saveRestaurantInput.name(),
            saveRestaurantInput.cnpj(),
            saveRestaurantInput.phone(),
            saveRestaurantInput.foodType(),
            saveRestaurantInput.openHour(),
            saveRestaurantInput.closeHour(),
            saveRestaurantInput.alwaysOpen(),
            saveRestaurantInput.totalCapacity(),
            saveRestaurantInput.totalGrade(),
            new AddressEntity(
                saveRestaurantInput.address().cep(),
                saveRestaurantInput.address().publicPlace(),
                saveRestaurantInput.address().complement(),
                saveRestaurantInput.address().neighborhood(),
                saveRestaurantInput.address().city(),
                saveRestaurantInput.address().ufState(),
                saveRestaurantInput.address().latitude(),
                saveRestaurantInput.address().longitude()
            )
        );

        this.saveRestaurantRepository.saveRestaurant(restaurantEntity);
        this.saveRestaurantOutput = new SaveRestaurantOutput(restaurantEntity, new OutputStatus(
            201,
        "Created",
        "Resturant created"
        ));

    }

}
