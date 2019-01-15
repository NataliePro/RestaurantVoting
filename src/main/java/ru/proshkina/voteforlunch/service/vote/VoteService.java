package ru.proshkina.voteforlunch.service.vote;

import ru.proshkina.voteforlunch.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteService {

    Vote createOrUpdate(Vote vote, int user_id, int restaurant_id);

    void delete(int id, int user_id);

    Vote get(int id, int user_id);

    Vote getByUserAndDate(int userId, LocalDate date);

    List<Vote> getAllByDate(LocalDate date);

    List<Vote> getAllByUser(int user_id);

    List<Vote> getAll();

    List<Vote> getAllByRestaurant(int restaurant_id);

    List<Vote> getAllByRestaurantAndUser(int restaurant_id, int user_id);

    List<Vote> getAllByRestaurantAndDate(int restaurant_id, LocalDate date);

    Vote getByRestaurantAndDateAndUser(int restaurant_id, LocalDate date, int user_id);
}
