package ru.proshkina.restaurantvoting.service.restaurant;

import ru.proshkina.restaurantvoting.TestUtil;
import ru.proshkina.restaurantvoting.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

public class RestaurantTestData {
    public static final Integer RESTAURANT1_ID = 100004;
    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID, "Restaurant1");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT1_ID + 1, "Restaurant2");
    public static final Restaurant RESTAURANT3 = new Restaurant(RESTAURANT1_ID + 2, "Restaurant3");
    public static final Restaurant RESTAURANT4 = new Restaurant(RESTAURANT1_ID + 3, "Restaurant4");
    public static final Restaurant RESTAURANT5 = new Restaurant(RESTAURANT1_ID + 4, "Restaurant5");

    public static final LocalDate TEST_DATE = LocalDate.of(2018, 10, 26);


    public static void assertMatch(Restaurant actual, Restaurant expected) {
        TestUtil.assertMatch(actual, expected, "dishes", "votes");
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        TestUtil.assertMatch(actual, List.of(expected), "dishes", "votes");
    }
}
