package com.fiap.restaurant.review.domain.output.restaurant;


import com.fiap.restaurant.review.domain.entities.restaurant.RestaurantEntity;
import com.fiap.restaurant.review.domain.generic.output.OutputInterface;
import com.fiap.restaurant.review.domain.generic.output.OutputStatus;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Data
@Getter
@Setter
@RequiredArgsConstructor
public class FilterRestaurantsOutput implements OutputInterface{

    private List<RestaurantEntity> listRestaurantEntity;
    private OutputStatus outputStatus;

    public FilterRestaurantsOutput(List<RestaurantEntity> listRestaurantEntity, OutputStatus outputStatus) {
        this.listRestaurantEntity = listRestaurantEntity;
        this.outputStatus = outputStatus;
    }

    @Override
    public Object getBody() {
        return this.listRestaurantEntity;
    }
}
