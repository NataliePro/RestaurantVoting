package ru.proshkina.voteforlunch.repository.vote;

import ru.proshkina.voteforlunch.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteRepository {

    Vote save(Vote vote, int user_id, int restaurant_id);

    boolean delete(int id, int user_id);

    Vote get(int id, int user_id);

    // null if not found
    Vote getByUserAndDate(int user_id, LocalDate date);

    List<Vote> getAllByDate(LocalDate date);

    List<Vote> getAllByUser(int user_id);

    List<Vote> getAll();

    List<Vote> getAllByRestaurant(int restaurant_id);

    List<Vote> getAllByRestaurantAndUser(int restaurant_id, int user_id);

    List<Vote> getAllByRestaurantAndDate(int restaurant_id, LocalDate date);

    Vote getByRestaurantAndDateAndUser(int restaurant_id, LocalDate date, int user_id);
}
