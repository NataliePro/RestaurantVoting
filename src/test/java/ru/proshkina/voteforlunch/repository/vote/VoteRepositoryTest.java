package ru.proshkina.voteforlunch.repository.vote;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.proshkina.voteforlunch.model.Vote;
import ru.proshkina.voteforlunch.repository.AbstractRepositoryTest;
import ru.proshkina.voteforlunch.to.VoteTo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static ru.proshkina.voteforlunch.repository.TestUtil.assertMatch;
import static ru.proshkina.voteforlunch.repository.restaurant.RestaurantTestData.RESTAURANT1_ID;
import static ru.proshkina.voteforlunch.repository.user.UserTestData.ADMIN_ID;
import static ru.proshkina.voteforlunch.repository.user.UserTestData.USER_ID;
import static ru.proshkina.voteforlunch.repository.vote.VoteTestData.*;
import static ru.proshkina.voteforlunch.util.VoteUtil.getVoteToList;

public class VoteRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    VoteRepository voteRepository;

    @Test
    public void save() {
        Vote newVote = new Vote(null, LocalDate.of(2018, 10, 26), LocalTime.of(8, 0, 0));
        Vote createdVote = voteRepository.save(newVote, RESTAURANT1_ID, ADMIN_ID);
        newVote.setId(createdVote.getId());
        newVote.setUser(createdVote.getUser());
        newVote.setRestaurant(createdVote.getRestaurant());
        assertMatch(voteRepository.getAll(TEST_DATE), Arrays.asList(newVote, VOTE2, VOTE, VOTE3));
    }

    @Test
    public void getByUser() {
        assertMatch(voteRepository.getByUser(TEST_DATE, USER_ID), VOTE);
    }

    @Test
    public void getByNotFoundUser() {
        Assert.assertNull(voteRepository.getByUser(TEST_DATE, 123));
    }


    @Test
    public void getAll() {
        List<VoteTo> list = getVoteToList(voteRepository.getAll(TEST_DATE));
        assertMatch(voteRepository.getAll(TEST_DATE), Arrays.asList(VOTE2, VOTE, VOTE3));
    }
}