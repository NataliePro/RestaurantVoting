package ru.proshkina.voteforlunch.repository.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.proshkina.voteforlunch.DateTimeFactory;
import ru.proshkina.voteforlunch.model.Vote;
import ru.proshkina.voteforlunch.repository.restaurant.CrudRestaurantRepository;
import ru.proshkina.voteforlunch.repository.user.CrudUserRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class VoteRepositoryImpl implements VoteRepository {

    private CrudVoteRepository voteRepository;

    private CrudUserRepository userRepository;

    private CrudRestaurantRepository restaurantRepository;

    private DateTimeFactory dateTimeFactory;

    @Autowired
    public VoteRepositoryImpl(CrudVoteRepository voteRepository,
                              CrudUserRepository userRepository,
                              CrudRestaurantRepository restaurantRepository,
                              DateTimeFactory dateTimeFactory) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
        this.dateTimeFactory = dateTimeFactory;
    }

    @Override
    @Transactional
    public Vote save(Vote vote, int user_id, int restaurant_id) {
        if (!vote.isNew() && get(vote.getId(), user_id) == null) {
            return null;
        }
        if (vote.isNew()) {
            vote.setTime(dateTimeFactory.getCurrentTime());
            vote.setDate(dateTimeFactory.getCurrentDate());
        }
        vote.setUser(userRepository.getOne(user_id));
        vote.setRestaurant(restaurantRepository.getOne(restaurant_id));
        return voteRepository.save(vote);
    }

    @Override
    public Vote getByUserAndDate(int user_id, LocalDate date) {
        return voteRepository.findAllByDateAndUser_Id(date, user_id);
    }

    @Override
    public List<Vote> getAllByDate(LocalDate date) {
        return voteRepository.findAllByDateOrderByTimeAsc(date);
    }

    @Override
    public boolean delete(int id, int user_id) {
        return voteRepository.delete(id, user_id) != 0;
    }

    @Override
    public Vote get(int id, int user_id) {
        return voteRepository.findById(id).filter(vote -> vote.getUser().getId() == user_id).orElse(null);
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
