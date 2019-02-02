package ru.proshkina.restaurantvoting.service.restaurant;

import ru.proshkina.restaurantvoting.model.Restaurant;
import ru.proshkina.restaurantvoting.to.RestaurantTo;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantService {

    Restaurant create(Restaurant restaurant);

    void update(Restaurant restaurant);

    Restaurant get(int id);

    void delete(int id);

    List<Restaurant> getAll();

    List<Restaurant> getAllWithDishesByDate(LocalDate date);

    List<RestaurantTo> getAllWithVotesByDate(LocalDate date);
}
