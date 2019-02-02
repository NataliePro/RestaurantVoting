package ru.proshkina.restaurantvoting.service.vote;

import ru.proshkina.restaurantvoting.TestUtil;
import ru.proshkina.restaurantvoting.model.Vote;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.proshkina.restaurantvoting.service.restaurant.RestaurantTestData.RESTAURANT4;
import static ru.proshkina.restaurantvoting.service.user.UserTestData.USER;

public class VoteTestData {

    public static final LocalDate TEST_DATE = LocalDate.of(2018, 10, 26);
    public static final LocalTime TEST_TIME_BEFORE_LIMIT = LocalTime.of(9, 30);
    public static final LocalTime TEST_TIME_AFTER_LIMIT = LocalTime.of(12, 30);
    public static final LocalTime TEST_TIME_LIMIT = LocalTime.of(11, 00);
    public static final Integer VOTE_ID = 100009;
    public static final Vote VOTE = new Vote(VOTE_ID, LocalDate.of(2018, 10, 26), LocalTime.of(10, 0, 0));
    public static final Vote VOTE2 = new Vote(VOTE_ID + 1, LocalDate.of(2018, 10, 26), LocalTime.of(9, 0, 0));
    public static final Vote VOTE3 = new Vote(VOTE_ID + 2, LocalDate.of(2018, 10, 25), LocalTime.of(9, 0, 0));
    public static final Vote VOTE4 = new Vote(VOTE_ID + 3, LocalDate.of(2018, 10, 26), LocalTime.of(10, 30, 0));

    public static Vote getUpdated() {
        return new Vote(VOTE_ID, TEST_DATE, LocalTime.of(11, 29), USER, RESTAURANT4);
    }

    public static void assertMatch(Vote actual, Vote expected) {
        TestUtil.assertMatch(actual, expected, "user", "restaurant");
    }

    public static void assertMatch(Iterable<Vote> actual, Vote... expected) {
        TestUtil.assertMatch(actual, List.of(expected), "user", "restaurant");
    }
}
