package ru.proshkina.voteforlunch.repository.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.proshkina.voteforlunch.model.Restaurant;
import ru.proshkina.voteforlunch.model.User;
import ru.proshkina.voteforlunch.model.Vote;
import ru.proshkina.voteforlunch.repository.restaurant.CrudRestaurantRepository;
import ru.proshkina.voteforlunch.repository.user.CrudUserRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class VoteRepositoryImpl implements VoteRepository {

    private CrudVoteRepository voteRepository;

    private CrudRestaurantRepository restaurantRepository;

    private CrudUserRepository userRepository;

    @Autowired
    public VoteRepositoryImpl(CrudVoteRepository voteRepository, CrudRestaurantRepository restaurantRepository, CrudUserRepository userRepository) {
        this.voteRepository = voteRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Vote save(Vote vote, Integer restaurant_id, Integer user_id) {
        Restaurant restaurant = restaurantRepository.findById(restaurant_id).orElse(null);
        User user = userRepository.findById(user_id).orElse(null);
        if ((!vote.isNew() && getByUser(vote.getDate(), user_id) == null) || restaurant == null || user == null) {
            return null;
        }
        //много запросов к бд (((( переделать
        vote.setRestaurant(restaurant);
        vote.setUser(user);
        return voteRepository.save(vote);
    }

    @Override
    public Vote getByUser(LocalDate date, int user_id) {
        return voteRepository.findByDateAndUser(date, user_id);
    }

    @Override
    public List<Vote> getAll(LocalDate date) {
        return voteRepository.findAllByDate(date);
    }

}
