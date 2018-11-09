package ru.proshkina.voteforlunch.service.restaurant;

import ru.proshkina.voteforlunch.model.Restaurant;

public class RestaurantTestData {
    public static final Integer RESTAURANT1_ID = 100004;
    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID, "Restaurant1");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT1_ID + 1, "Restaurant2");
    public static final Restaurant RESTAURANT3 = new Restaurant(RESTAURANT1_ID + 2, "Restaurant3");
    public static final Restaurant RESTAURANT4 = new Restaurant(RESTAURANT1_ID + 3, "Restaurant4");
}
