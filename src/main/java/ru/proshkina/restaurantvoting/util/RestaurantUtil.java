package ru.proshkina.restaurantvoting.util;

import ru.proshkina.restaurantvoting.model.Restaurant;
import ru.proshkina.restaurantvoting.to.RestaurantTo;

import java.time.LocalDate;

public class RestaurantUtil {

    public static RestaurantTo asTo(Restaurant restaurant, int voteCount, LocalDate date) {
        return new RestaurantTo(restaurant.getId(), restaurant.getName(), voteCount, date);
    }
}
