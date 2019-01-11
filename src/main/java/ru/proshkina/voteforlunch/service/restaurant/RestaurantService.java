package ru.proshkina.voteforlunch.service.restaurant;

import ru.proshkina.voteforlunch.model.Restaurant;
import ru.proshkina.voteforlunch.to.RestaurantTo;

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
