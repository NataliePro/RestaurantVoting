package ru.proshkina.voteforlunch.service.vote;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import ru.proshkina.voteforlunch.AbstractServiceTest;
import ru.proshkina.voteforlunch.DateTimeFactory;
import ru.proshkina.voteforlunch.model.Vote;
import ru.proshkina.voteforlunch.repository.vote.VoteRepositoryImpl;
import ru.proshkina.voteforlunch.util.exception.NotFoundException;
import ru.proshkina.voteforlunch.util.exception.VotingTimeIsOutException;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.proshkina.voteforlunch.service.restaurant.RestaurantTestData.RESTAURANT1_ID;
import static ru.proshkina.voteforlunch.service.user.UserTestData.ADMIN_ID;
import static ru.proshkina.voteforlunch.service.user.UserTestData.USER_ID;
import static ru.proshkina.voteforlunch.service.vote.VoteTestData.*;

public class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    private VoteService service;

    @Mock
    private DateTimeFactory timeFactory;

    @Mock
    private VoteRepositoryImpl mockRepository;

    @InjectMocks
    private VoteServiceImpl mockService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateBeforeTimeLimit() {
        Vote newVote = new Vote(null, TEST_DATE, LocalTime.of(8, 0, 0));
        when(timeFactory.getCurrentTime()).thenReturn(TEST_TIME_BEFORE_LIMIT);
        when(timeFactory.getCurrentDate()).thenReturn(TEST_DATE);
        when(timeFactory.getTimeLimit()).thenReturn(TEST_TIME_LIMIT);
        when(mockRepository.save(newVote, ADMIN_ID, RESTAURANT1_ID)).thenReturn(newVote);
        Vote createdVote = mockService.create(newVote, ADMIN_ID, RESTAURANT1_ID);
        assertMatch(createdVote, newVote);
        verify(mockRepository).save(newVote, ADMIN_ID, RESTAURANT1_ID);
    }

    @Test
    void testCreateAfterTimeLimit() {
        Vote newVote = new Vote(null, TEST_DATE, LocalTime.of(8, 0, 0));
        when(timeFactory.getCurrentTime()).thenReturn(TEST_TIME_AFTER_LIMIT);
        when(timeFactory.getCurrentDate()).thenReturn(TEST_DATE);
        when(timeFactory.getTimeLimit()).thenReturn(TEST_TIME_LIMIT);
        assertThrows(VotingTimeIsOutException.class, () ->
                mockService.create(newVote, ADMIN_ID, RESTAURANT1_ID));
    }

    @Test
    void testDelete() {
        service.delete(VOTE_ID, USER_ID);
        assertMatch(service.getAllByDate(TEST_DATE), VOTE2, VOTE4);
    }

    @Test
    void testDeleteNotFound() {
        assertThrows(NotFoundException.class, () ->
                service.delete(123, USER_ID));
    }

    @Test
    void testGetNotFound() {
        assertThrows(NotFoundException.class, () ->
                service.get(123, USER_ID));
    }

    @Test
    void testGetByUserAndDate() {
        assertMatch(service.getByUserAndDate(USER_ID, TEST_DATE), VOTE);
    }

    @Test
    void testGetByNotFoundUser() {
        assertThrows(NotFoundException.class, () ->
                service.getByUserAndDate(123, TEST_DATE));
    }

    @Test
    void testGetAll() {
        assertMatch(service.getAll(), VOTE3, VOTE2, VOTE, VOTE4);
    }

    @Test
    void testGetAllByDate() {
        assertMatch(service.getAllByDate(TEST_DATE), VOTE2, VOTE, VOTE4);
    }

    @Test
    void testGetAllByUser() {
        assertMatch(service.getAllByUser(USER_ID), VOTE3, VOTE);
    }

    @Test
    void testGetAllByRestaurant() {
        assertMatch(service.getAllByRestaurant(RESTAURANT1_ID), VOTE3, VOTE, VOTE4);
    }

    @Test
    void testGetAllByRestaurantAndUser() {
        assertMatch(service.getAllByRestaurantAndUser(RESTAURANT1_ID, USER_ID), VOTE3, VOTE);
    }

    @Test
    void testGetAllByRestaurantAndDate() {
        assertMatch(service.getAllByRestaurantAndDate(RESTAURANT1_ID, TEST_DATE), VOTE, VOTE4);
    }

    @Test
    void testGetByRestaurantAndDateAndUser() {
        assertMatch(service.getByRestaurantAndDateAndUser(RESTAURANT1_ID, TEST_DATE, USER_ID), VOTE);
    }

}