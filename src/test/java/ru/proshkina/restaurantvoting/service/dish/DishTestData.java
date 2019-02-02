package ru.proshkina.restaurantvoting.service.dish;

import ru.proshkina.restaurantvoting.TestUtil;
import ru.proshkina.restaurantvoting.model.Dish;

import java.time.LocalDate;
import java.util.List;

public class DishTestData {

    public static final Integer DISH1_ID = 100013;
    public static final Dish DISH1 = new Dish(DISH1_ID, "Dish1", LocalDate.of(2018, 10, 26), 2000);
    public static final Dish DISH2 = new Dish(DISH1_ID + 1, "Dish2", LocalDate.of(2018, 10, 26), 1000);
    public static final Dish DISH3 = new Dish(DISH1_ID + 2, "Dish3", LocalDate.of(2018, 10, 26), 1500);
    public static final Dish DISH4 = new Dish(DISH1_ID + 3, "Dish4", LocalDate.of(2018, 10, 26), 1300);
    public static final Dish DISH5 = new Dish(DISH1_ID + 4, "Dish5", LocalDate.of(2018, 10, 26), 1450);
    public static final Dish DISH6 = new Dish(DISH1_ID + 5, "Dish6", LocalDate.of(2018, 10, 26), 1700);
    public static final Dish DISH7 = new Dish(DISH1_ID + 6, "Dish7", LocalDate.of(2018, 10, 26), 2000);
    public static final Dish DISH8 = new Dish(DISH1_ID + 7, "Dish8", LocalDate.of(2018, 10, 26), 2200);
    public static final Dish DISH9 = new Dish(DISH1_ID + 8, "Dish9", LocalDate.of(2018, 10, 26), 1800);
    public static final Dish DISH10 = new Dish(DISH1_ID + 9, "Dish10", LocalDate.of(2018, 10, 25), 2200);

    public static final Integer RESTAURANT1_ID = 100004;
    public static final Integer RESTAURANT2_ID = 100005;

    public static final LocalDate TEST_DATE = LocalDate.of(2018, 10, 26);

    public static Dish getUpdated() {
        return new Dish(DISH1_ID, "updated dish", TEST_DATE, 2500);
    }

    public static Dish getCreated() {
        return new Dish(null, "Блюдо111", LocalDate.of(2018, 10, 26), 2100);
    }

    public static void assertMatch(Dish actual, Dish expected) {
        TestUtil.assertMatch(actual, expected, "restaurant");
    }

    public static void assertMatch(Iterable<Dish> actual, Dish... expected) {
        TestUtil.assertMatch(actual, List.of(expected), "restaurant");
    }
}
