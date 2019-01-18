package ru.proshkina.voteforlunch.service.vote;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.proshkina.voteforlunch.DateTimeFactory;
import ru.proshkina.voteforlunch.model.Vote;
import ru.proshkina.voteforlunch.repository.CrudRestaurantRepository;
import ru.proshkina.voteforlunch.repository.CrudUserRepository;
import ru.proshkina.voteforlunch.repository.CrudVoteRepository;
import ru.proshkina.voteforlunch.util.exception.VotingTimeIsOutException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.proshkina.voteforlunch.util.ValidationUtil.checkNotFound;
import static ru.proshkina.voteforlunch.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteServiceImpl implements VoteService {

    private final CrudVoteRepository voteRepository;
    private final CrudRestaurantRepository restaurantRepository;
    private final CrudUserRepository userRepository;
    private final DateTimeFactory dateTimeFactory;

    public VoteServiceImpl(CrudVoteRepository voteRepository,
                           CrudRestaurantRepository restaurantRepository,
                           CrudUserRepository userRepository,
                           DateTimeFactory dateTimeFactory) {
        this.voteRepository = voteRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
        this.dateTimeFactory = dateTimeFactory;
    }

    @Transactional
    @Override
    public Vote createOrUpdate(Vote vote, int user_id, int restaurant_id) {
        Assert.notNull(vote, "vote must not be null");
        LocalTime currentTime = dateTimeFactory.getCurrentTime();
        LocalDate currentDate = dateTimeFactory.getCurrentDate();
        LocalTime timeLimit = dateTimeFactory.getTimeLimit();
        if (currentTime.isBefore(timeLimit)) {
            Vote existVote = voteRepository.findAllByDateAndUser_Id(currentDate, user_id);
            if (existVote != null) {
                voteRepository.delete(existVote.getId(), user_id);
            }
            vote.setTime(currentTime);
            vote.setDate(currentDate);
            vote.setUser(userRepository.getOne(user_id));
            vote.setRestaurant(restaurantRepository.getOne(restaurant_id));
            return voteRepository.save(vote);
        }
        throw new VotingTimeIsOutException("Time to vote is out!");
    }

    @Override
    public void delete(int id, int user_id) {
        checkNotFoundWithId(voteRepository.delete(id, user_id) != 0, id);
    }

    @Override
    public Vote get(int id, int user_id) {
        return checkNotFoundWithId(voteRepository.findById(id).filter(vote -> vote.getUser().getId() == user_id).orElse(null), id);
    }

    @Override
    public Vote getByUserAndDate(int user_id, LocalDate date) {
        return checkNotFound(voteRepository.findAllByDateAndUser_Id(date, user_id), "Not found vote for user " + user_id + " and date " + date);
    }

    @Override
    public List<Vote> getAllByDate(LocalDate date) {
        return voteRepository.findAllByDateOrderByTimeAsc(date);
    }

    @Override
    public List<Vote> getAllByUser(int user_id) {
        return voteRepository.findAllByUser_IdOrderByDate(user_id);
    }

    @Override
    public List<Vote> getAll() {
        return voteRepository.getAll();
    }

    @Override
    public List<Vote> getAllByRestaurant(int restaurant_id) {
        return voteRepository.findAllByRestaurant_IdOrderByDateAscIdAsc(restaurant_id);
    }

    @Override
    public List<Vote> getAllByRestaurantAndUser(int restaurant_id, int user_id) {
        return voteRepository.findAllByRestaurant_IdAndUser_IdOrderByDateAscIdAsc(restaurant_id, user_id);
    }

    @Override
    public List<Vote> getAllByRestaurantAndDate(int restaurant_id, LocalDate date) {
        return voteRepository.findAllByRestaurant_IdAndDateOrderByDateAscIdAsc(restaurant_id, date);
    }

    @Override
    public Vote getByRestaurantAndDateAndUser(int restaurant_id, LocalDate date, int user_id) {
        return voteRepository.findAllByRestaurant_IdAndDateAndUser_Id(restaurant_id, date, user_id);
    }
}
