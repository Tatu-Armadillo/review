package com.fiap.restaurant.review.domain.presenters.table;

import java.util.HashMap;
import java.util.Map;

import com.fiap.restaurant.review.domain.entities.table.TableEntity;
import com.fiap.restaurant.review.domain.generic.presenter.PresenterInterface;
import com.fiap.restaurant.review.domain.output.tables.SaveTablesOutput;

public class SaveTablePresenter implements PresenterInterface {
    private final SaveTablesOutput saveTableOutput;

    public SaveTablePresenter(SaveTablesOutput saveTableOutput) {
        this.saveTableOutput = saveTableOutput;
    }

    public Map<String, Object> toArray() {
        Map<String, Object> array = new HashMap<>();
        TableEntity table = this.saveTableOutput.getTableEnity();

        array.put("capacity", table.getCapacity());
        array.put("available", table.getAvailable());

        Map<String, Object> restaurantMap = new HashMap<>();
        restaurantMap.put("restaurant_id", table.getRestaurant().getId());
        restaurantMap.put("restaurant_name", table.getRestaurant().getName());
        restaurantMap.put("restaurant_cnpj", table.getRestaurant().getCnpj());
        restaurantMap.put("phone", table.getRestaurant().getPhone());
        restaurantMap.put("food_type", table.getRestaurant().getFoodType());
        restaurantMap.put("open_hour", table.getRestaurant().getOpenHour().toString());
        restaurantMap.put("close_hour", table.getRestaurant().getCloseHour().toString());
        array.put("restaurant", restaurantMap);

        return array;
    }

    public SaveTablesOutput getOutput() {
        return this.saveTableOutput;
    }
}
