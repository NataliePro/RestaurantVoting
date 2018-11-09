package ru.proshkina.voteforlunch.service.dish;

import ru.proshkina.voteforlunch.model.Dish;

import java.time.LocalDate;
import java.util.List;

public interface DishService {

    Dish create(Dish dish, int restaurant_id);

    Dish get(int id);

    void delete(int id);

    void update(Dish dish, int restaurant_id);

    List<Dish> getAll(LocalDate date);

    List<Dish> getAllByRestaurant(LocalDate date, int restaurant);
}
