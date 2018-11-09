package ru.proshkina.voteforlunch.service.vote;

import ru.proshkina.voteforlunch.model.Vote;

import java.time.LocalDate;
import java.time.LocalTime;

import static ru.proshkina.voteforlunch.service.restaurant.RestaurantTestData.*;
import static ru.proshkina.voteforlunch.service.user.UserTestData.*;

public class VoteTestData {

    public static final LocalDate TEST_DATE = LocalDate.of(2018, 10, 26);
    public static final Integer VOTE_ID = 100008;
    public static final Vote VOTE = new Vote(VOTE_ID, LocalDate.of(2018, 10, 26), LocalTime.of(10, 0, 0), USER, RESTAURANT1);
    public static final Vote VOTE2 = new Vote(VOTE_ID + 1, LocalDate.of(2018, 10, 26), LocalTime.of(9, 0, 0), USER2, RESTAURANT3);
    public static final Vote VOTE3 = new Vote(VOTE_ID + 2, LocalDate.of(2018, 10, 26), LocalTime.of(10, 30, 0), USER3, RESTAURANT1);

    public static Vote getUpdated() {
        return new Vote(VOTE_ID, TEST_DATE, LocalTime.of(11, 29), USER, RESTAURANT4);
    }

}
