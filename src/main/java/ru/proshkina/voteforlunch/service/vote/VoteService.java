package ru.proshkina.voteforlunch.service.vote;

import ru.proshkina.voteforlunch.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteService {

    Vote create(Vote vote, int userId);

    void update(Vote vote, int userId);

    void delete(int id);

    Vote get(int id);

    Vote getByUser(LocalDate date, int userId);

    List<Vote> getAll(LocalDate date);
}
