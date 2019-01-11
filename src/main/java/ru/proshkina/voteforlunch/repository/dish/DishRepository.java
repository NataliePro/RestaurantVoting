package ru.proshkina.voteforlunch.repository.dish;

import ru.proshkina.voteforlunch.model.Dish;

import java.time.LocalDate;
import java.util.List;

public interface DishRepository {

    Dish save(Dish dish, int restaurant_id);

    // false if not found
    boolean delete(int id, int restaurant_id);

    // null if not found
    Dish get(int id, int restaurant_id);

    List<Dish> getAll();

    List<Dish> getAllByDate(LocalDate date);

    List<Dish> getAllByRestaurantAndDate(int restaurant_id, LocalDate date);

    List<Dish> getAllByRestaurant(int restaurant_id);
}
