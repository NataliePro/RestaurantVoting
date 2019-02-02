package ru.proshkina.restaurantvoting.service.vote;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.proshkina.restaurantvoting.DateTimeFactory;
import ru.proshkina.restaurantvoting.model.Vote;
import ru.proshkina.restaurantvoting.repository.CrudRestaurantRepository;
import ru.proshkina.restaurantvoting.repository.CrudUserRepository;
import ru.proshkina.restaurantvoting.repository.CrudVoteRepository;
import ru.proshkina.restaurantvoting.util.exception.VotingTimeIsOutException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.proshkina.restaurantvoting.util.ValidationUtil.checkNotFound;
import static ru.proshkina.restaurantvoting.util.ValidationUtil.checkNotFoundWithId;

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
    public Vote createOrUpdate(Vote vote, int userId, int restaurantId) {
        Assert.notNull(vote, "vote must not be null");
        LocalDate currentDate = dateTimeFactory.getCurrentDate();
        Vote existVote = voteRepository.findAllByDateAndUser_Id(currentDate, userId);
        LocalTime timeLimit = dateTimeFactory.getTimeLimit();
        LocalTime currentTime = dateTimeFactory.getCurrentTime();
        if (currentTime.isAfter(timeLimit)) {
            throw new VotingTimeIsOutException("Time to vote is out!");
        }
        if (existVote != null) {
            voteRepository.delete(existVote.getId(), userId);
        }
        vote.setTime(currentTime);
        vote.setDate(currentDate);
        vote.setUser(userRepository.getOne(userId));
        vote.setRestaurant(restaurantRepository.getOne(restaurantId));
        return voteRepository.save(vote);
    }

    @Override
    public void delete(int id, int userId) {
        checkNotFoundWithId(voteRepository.delete(id, userId) != 0, id);
    }

    @Override
    public Vote get(int id, int userId) {
        return checkNotFoundWithId(voteRepository.findById(id).filter(vote -> vote.getUser().getId() == userId).orElse(null), id);
    }

    @Override
    public Vote getByUserAndDate(int userId, LocalDate date) {
        return checkNotFound(voteRepository.findAllByDateAndUser_Id(date, userId), "Not found vote for user " + userId + " and date " + date);
    }

    @Override
    public List<Vote> getAllByDate(LocalDate date) {
        return voteRepository.findAllByDateOrderByTimeAsc(date);
    }

    @Override
    public List<Vote> getAllByUser(int userId) {
        return voteRepository.findAllByUser_IdOrderByDate(userId);
    }

    @Override
    public List<Vote> getAll() {
        return voteRepository.getAll();
    }

    @Override
    public List<Vote> getAllByRestaurant(int restaurantId) {
        return voteRepository.findAllByRestaurant_IdOrderByDateAscIdAsc(restaurantId);
    }

    @Override
    public List<Vote> getAllByRestaurantAndUser(int restaurantId, int userId) {
        return voteRepository.findAllByRestaurant_IdAndUser_IdOrderByDateAscIdAsc(restaurantId, userId);
    }

    @Override
    public List<Vote> getAllByRestaurantAndDate(int restaurantId, LocalDate date) {
        return voteRepository.findAllByRestaurant_IdAndDateOrderByDateAscIdAsc(restaurantId, date);
    }

    @Override
    public Vote getByRestaurantAndDateAndUser(int restaurantId, LocalDate date, int userId) {
        return voteRepository.findAllByRestaurant_IdAndDateAndUser_Id(restaurantId, date, userId);
    }
}
