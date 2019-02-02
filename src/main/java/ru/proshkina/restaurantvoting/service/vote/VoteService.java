package ru.proshkina.restaurantvoting.service.vote;

import ru.proshkina.restaurantvoting.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteService {

    Vote createOrUpdate(Vote vote, int userId, int restaurantId);

    void delete(int id, int userId);

    Vote get(int id, int userId);

    Vote getByUserAndDate(int userId, LocalDate date);

    List<Vote> getAllByDate(LocalDate date);

    List<Vote> getAllByUser(int userId);

    List<Vote> getAll();

    List<Vote> getAllByRestaurant(int restaurantId);

    List<Vote> getAllByRestaurantAndUser(int restaurantId, int userId);

    List<Vote> getAllByRestaurantAndDate(int restaurantId, LocalDate date);

    Vote getByRestaurantAndDateAndUser(int restaurantId, LocalDate date, int userId);
}
