package ru.proshkina.voteforlunch.repository.vote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public Vote save(Vote vote, int user_id) {
        User user = userRepository.findById(user_id).orElse(null);
        Vote userVote = getByUser(vote.getDate(), user_id);
        if (user == null || (!vote.isNew() && (userVote == null || vote.getUser().getId() != user_id))) {
            return null;
        }
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

    @Override
    public boolean delete(int id) {
        return voteRepository.delete(id) != 0;
    }

    @Override
    public Vote get(int id) {
        return voteRepository.findById(id).orElse(null);
    }
}
