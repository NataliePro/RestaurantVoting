package ru.proshkina.voteforlunch.service.dish;

import ru.proshkina.voteforlunch.model.Dish;

import java.time.LocalDate;
import java.util.List;

public interface DishService {

    Dish create(Dish dish, int restaurant_id);

    Dish get(int id, int restaurant_id);

    void delete(int id, int restaurant_id);

    void update(Dish dish, int restaurant_id);

    List<Dish> getAll();

    List<Dish> getAllForDate(LocalDate date);

    List<Dish> getAllByRestaurant(int restaurant_id);

    List<Dish> getAllByRestaurantAndDate(int restaurant_id, LocalDate date);
}
