package com.food.ordering.system.ports.output.repository;

import com.food.ordering.system.domain.valueobject.ResturantID;
import com.food.ordering.system.entity.Resturant;

import java.util.Optional;

public interface RestaurantRepository {
    Optional<Resturant> findRestaurantDetails(Resturant resturant);
}
