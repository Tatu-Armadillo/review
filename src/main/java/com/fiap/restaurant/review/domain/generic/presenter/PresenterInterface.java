package com.fiap.restaurant.review.domain.generic.presenter;

import java.util.Map;

import com.fiap.restaurant.review.domain.generic.output.OutputInterface;

public interface PresenterInterface {
    Map<String, Object> toArray();

    OutputInterface getOutput();
}
