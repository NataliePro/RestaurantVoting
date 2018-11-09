package ru.proshkina.voteforlunch.service.vote;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.proshkina.voteforlunch.model.Vote;
import ru.proshkina.voteforlunch.repository.vote.VoteRepository;

import java.time.LocalDate;
import java.util.List;

import static ru.proshkina.voteforlunch.util.ValidationUtil.checkNotFound;
import static ru.proshkina.voteforlunch.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteServiceImpl implements VoteService {

    private VoteRepository repository;

    public VoteServiceImpl(VoteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Vote create(Vote vote, int userId) {
        Assert.notNull(vote, "vote must not be null");
        return repository.save(vote, userId);
    }

    @Override
    public void update(Vote vote, int userId) {
        Assert.notNull(vote, "vote must not be null");
        checkNotFoundWithId(repository.save(vote, userId), vote.getId());
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public Vote get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public Vote getByUser(LocalDate date, int userId) {
        return checkNotFound(repository.getByUser(date, userId), "Vote not found for user_id=" + userId);
    }

    @Override
    public List<Vote> getAll(LocalDate date) {
        return repository.getAll(date);
    }
}
