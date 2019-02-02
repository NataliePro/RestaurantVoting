package ru.proshkina.restaurantvoting.service.dish;

import ru.proshkina.restaurantvoting.model.Dish;

import java.time.LocalDate;
import java.util.List;

public interface DishService {

    Dish create(Dish dish, int restaurantId);

    Dish get(int id, int restaurantId);

    void delete(int id, int restaurantId);

    void update(Dish dish, int restaurantId);

    List<Dish> getAll();

    List<Dish> getAllForDate(LocalDate date);

    List<Dish> getAllByRestaurant(int restaurantId);

    List<Dish> getAllByRestaurantAndDate(int restaurantId, LocalDate date);
}
