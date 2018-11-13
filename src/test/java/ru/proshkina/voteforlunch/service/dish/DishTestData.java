package ru.proshkina.voteforlunch.service.dish;

import ru.proshkina.voteforlunch.model.Dish;

import java.time.LocalDate;

public class DishTestData {

    public static final Integer DISH1_ID = 100013;
    public static final Dish DISH1 = new Dish(DISH1_ID, "Dish1", LocalDate.of(2018, 10, 26), 20000);
    public static final Dish DISH2 = new Dish(DISH1_ID + 1, "Dish2", LocalDate.of(2018, 10, 26), 10000);
    public static final Dish DISH3 = new Dish(DISH1_ID + 2, "Dish3", LocalDate.of(2018, 10, 26), 15000);
    public static final Dish DISH4 = new Dish(DISH1_ID + 3, "Dish4", LocalDate.of(2018, 10, 26), 13000);
    public static final Dish DISH5 = new Dish(DISH1_ID + 4, "Dish5", LocalDate.of(2018, 10, 26), 14500);
    public static final Dish DISH6 = new Dish(DISH1_ID + 5, "Dish6", LocalDate.of(2018, 10, 26), 17000);
    public static final Dish DISH7 = new Dish(DISH1_ID + 6, "Dish7", LocalDate.of(2018, 10, 26), 20000);
    public static final Dish DISH8 = new Dish(DISH1_ID + 7, "Dish8", LocalDate.of(2018, 10, 26), 22000);
    public static final Dish DISH9 = new Dish(DISH1_ID + 8, "Dish9", LocalDate.of(2018, 10, 26), 18000);

    public static final Integer RESTAURANT1_ID = 100004;
    public static final Integer RESTAURANT2_ID = 100005;

    public static final LocalDate TEST_DATE = LocalDate.of(2018, 10, 26);

    public static Dish getUpdated() {
        return new Dish(DISH1_ID, "updated dish", TEST_DATE, 2500);
    }

    public static Dish getCreated() {
        return new Dish(null, "Блюдо111", LocalDate.of(2018, 10, 26), 21000);
    }
}
