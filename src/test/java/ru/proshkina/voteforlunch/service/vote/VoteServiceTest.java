package ru.proshkina.voteforlunch.service.vote;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.proshkina.voteforlunch.AbstractServiceTest;
import ru.proshkina.voteforlunch.model.Vote;
import ru.proshkina.voteforlunch.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.proshkina.voteforlunch.TestUtil.assertMatch;
import static ru.proshkina.voteforlunch.service.restaurant.RestaurantTestData.RESTAURANT4;
import static ru.proshkina.voteforlunch.service.user.UserTestData.*;
import static ru.proshkina.voteforlunch.service.vote.VoteTestData.*;

public class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    private VoteService service;

    @Test
    public void create() {
        Vote newVote = new Vote(null, LocalDate.of(2018, 10, 26), LocalTime.of(8, 0, 0), USER, RESTAURANT4);
        Vote createdVote = service.create(newVote, ADMIN_ID);
        newVote.setId(createdVote.getId());
        newVote.setUser(createdVote.getUser());
        newVote.setRestaurant(createdVote.getRestaurant());
        assertMatch(service.getAll(TEST_DATE), List.of(newVote, VOTE2, VOTE, VOTE3));
    }

    @Test
    public void update() {
        Vote updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.getAll(TEST_DATE), List.of(VOTE2, VOTE3, updated));
    }

    @Test
    public void updateNotFoundUser() {
        thrown.expect(NotFoundException.class);
        Vote updated = getUpdated();
        service.update(updated, ADMIN_ID);
    }

    @Test
    public void delete() {
        service.delete(VOTE_ID);
        assertMatch(service.getAll(TEST_DATE), List.of(VOTE2, VOTE3));
    }

    @Test
    public void deleteNotFound() {
        thrown.expect(NotFoundException.class);
        service.delete(123);
    }

    @Test
    public void get() {
        assertMatch(service.get(VOTE_ID), VOTE, "user", "restaurant");
    }

    @Test
    public void getNotFound() {
        thrown.expect(NotFoundException.class);
        service.get(123);
    }

    @Test
    public void getByUser() {
        assertMatch(service.getByUser(TEST_DATE, USER_ID), VOTE);
    }

    @Test
    public void getByNotFoundUser() {
        thrown.expect(NotFoundException.class);
        service.getByUser(TEST_DATE, 123);
    }

    @Test
    public void getAll() {
        assertMatch(service.getAll(TEST_DATE), List.of(VOTE2, VOTE, VOTE3));
    }
}