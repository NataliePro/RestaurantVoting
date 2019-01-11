package ru.proshkina.voteforlunch.util;

import ru.proshkina.voteforlunch.model.Restaurant;
import ru.proshkina.voteforlunch.to.RestaurantTo;

import java.time.LocalDate;

public class RestaurantUtil {

    public static RestaurantTo asTo(Restaurant restaurant, int voteCount, LocalDate date) {
        return new RestaurantTo(restaurant.getId(), restaurant.getName(), voteCount, date);
    }
}
