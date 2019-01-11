package ru.proshkina.voteforlunch.service.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.proshkina.voteforlunch.DateTimeFactory;
import ru.proshkina.voteforlunch.model.Vote;
import ru.proshkina.voteforlunch.repository.vote.VoteRepository;
import ru.proshkina.voteforlunch.util.exception.VotingTimeIsOutException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.proshkina.voteforlunch.util.ValidationUtil.checkNotFound;
import static ru.proshkina.voteforlunch.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteServiceImpl implements VoteService {

    private final VoteRepository repository;

    @Autowired
    private DateTimeFactory dateTimeFactory;

    public VoteServiceImpl(VoteRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public Vote create(Vote vote, int userId, int restaurant_id) {
        Assert.notNull(vote, "vote must not be null");
        LocalTime currentTime = dateTimeFactory.getCurrentTime();
        LocalDate currentDate = dateTimeFactory.getCurrentDate();
        LocalTime timeLimit = dateTimeFactory.getTimeLimit();
        if (currentTime.isBefore(timeLimit)) {
            Vote existingVote = repository.getByUserAndDate(userId, currentDate);
            if (existingVote != null) {
                vote.setTime(currentTime);
                vote.setDate(currentDate);
                repository.delete(existingVote.getId(), userId);
            }
            return repository.save(vote, userId, restaurant_id);
        }
        throw new VotingTimeIsOutException("Time to vote is out!");
    }

    @Override
    public void delete(int id, int user_id) {
        checkNotFoundWithId(repository.delete(id, user_id), id);
    }

    @Override
    public Vote get(int id, int user_id) {
        return checkNotFoundWithId(repository.get(id, user_id), id);
    }

    @Override
    public Vote getByUserAndDate(int userId, LocalDate date) {
        return checkNotFound(repository.getByUserAndDate(userId, date), "Not found vote for user " + userId + " and date " + date);
    }

    @Override
    public List<Vote> getAllByDate(LocalDate date) {
        return repository.getAllByDate(date);
    }

    @Override
    public List<Vote> getAllByUser(int user_id) {
        return repository.getAllByUser(user_id);
    }

    @Override
    public List<Vote> getAll() {
        return repository.getAll();
    }

    @Override
    public List<Vote> getAllByRestaurant(int restaurant_id) {
        return repository.getAllByRestaurant(restaurant_id);
    }

    @Override
    public List<Vote> getAllByRestaurantAndUser(int restaurant_id, int user_id) {
        return repository.getAllByRestaurantAndUser(restaurant_id, user_id);
    }

    @Override
    public List<Vote> getAllByRestaurantAndDate(int restaurant_id, LocalDate date) {
        return repository.getAllByRestaurantAndDate(restaurant_id, date);
    }

    @Override
    public Vote getByRestaurantAndDateAndUser(int restaurant_id, LocalDate date, int user_id) {
        return repository.getByRestaurantAndDateAndUser(restaurant_id, date, user_id);
    }
}
