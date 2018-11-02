package ru.proshkina.voteforlunch.repository.dish;

import ru.proshkina.voteforlunch.model.Dish;

import java.time.LocalDate;
import java.util.List;

public interface DishRepository {

    Dish save(Dish dish, Integer restaurant_id);

    // false if not found
    boolean delete(int id);

    // null if not found
    Dish get(int id);

    List<Dish> getAll(LocalDate date);

    List<Dish> getAllByRestaraunt(LocalDate date, Integer restaurant_id);
}
