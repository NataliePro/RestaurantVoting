package ru.proshkina.voteforlunch.repository.vote;

import ru.proshkina.voteforlunch.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteRepository {

    Vote save(Vote vote, Integer restaurant_id, Integer user_id);

    // null if not found
    Vote getByUser(LocalDate date, int user_id);

    List<Vote> getAll(LocalDate date);
}
