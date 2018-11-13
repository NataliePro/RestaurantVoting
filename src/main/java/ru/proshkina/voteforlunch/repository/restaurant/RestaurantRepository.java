package ru.proshkina.voteforlunch.repository.restaurant;

import ru.proshkina.voteforlunch.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantRepository {

    Restaurant save(Restaurant restaurant);

    // false if not found
    boolean delete(int id);

    // null if not found
    Restaurant get(int id);

    List<Restaurant> getAll();

    List<Restaurant> getAllWithDishes(LocalDate date);

}
