package ru.proshkina.voteforlunch.repository;

import ru.proshkina.voteforlunch.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteRepository {

    Vote save(Vote vote);

    // false if not found
    boolean delete(int id);

    // null if not found
    Vote get(int id);

    // null if not found
    Vote getByUser(LocalDate date, int user_id);

    List<Vote> getAll(LocalDate date);

    List<Vote> getAllByRestaraunt(LocalDate date, int restaurant_id);
}
