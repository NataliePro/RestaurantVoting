package ru.proshkina.voteforlunch.service.restaurant;

import ru.proshkina.voteforlunch.model.Dish;
import ru.proshkina.voteforlunch.model.Restaurant;

import java.time.LocalDate;

public class RestaurantTestData {
    public static final Integer RESTAURANT1_ID = 100004;
    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID, "Restaurant1");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT1_ID + 1, "Restaurant2");
    public static final Restaurant RESTAURANT3 = new Restaurant(RESTAURANT1_ID + 2, "Restaurant3");
    public static final Restaurant RESTAURANT4 = new Restaurant(RESTAURANT1_ID + 3, "Restaurant4");
    public static final Restaurant RESTAURANT5 = new Restaurant(RESTAURANT1_ID + 4, "Restaurant5");

    public static final Integer DISH1_ID = 100013;
    public static final Dish DISH1 = new Dish(DISH1_ID, "Dish1", LocalDate.of(2018, 10, 26), 20000);
    public static final Dish DISH8 = new Dish(DISH1_ID + 7, "Dish8", LocalDate.of(2018, 10, 26), 22000);


    public static final LocalDate TEST_DATE = LocalDate.of(2018, 10, 26);
}
