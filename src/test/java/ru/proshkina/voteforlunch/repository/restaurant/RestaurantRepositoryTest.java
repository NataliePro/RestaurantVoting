package ru.proshkina.voteforlunch.repository.restaurant;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.proshkina.voteforlunch.model.Restaurant;
import ru.proshkina.voteforlunch.repository.AbstractRepositoryTest;

import java.util.Arrays;

import static ru.proshkina.voteforlunch.repository.TestUtil.assertMatch;
import static ru.proshkina.voteforlunch.repository.restaurant.RestaurantTestData.*;

public class RestaurantRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Test
    public void save() {
        Restaurant newRest = new Restaurant(null, "new Restaurant");
        Restaurant createdRest = restaurantRepository.save(newRest);
        newRest.setId(createdRest.getId());
        assertMatch(restaurantRepository.getAll(), Arrays.asList(RESTAURANT1, RESTAURANT2, RESTAURANT3, RESTAURANT4, newRest));
    }

    @Test
    public void delete() {
        Assert.assertTrue(restaurantRepository.delete(RESTAURANT1_ID));
        assertMatch(restaurantRepository.getAll(), Arrays.asList(RESTAURANT2, RESTAURANT3, RESTAURANT4));
    }

    @Test
    public void deleteNotFound() {
        Assert.assertFalse(restaurantRepository.delete(123));
    }

    @Test
    public void get() {
        assertMatch(restaurantRepository.get(RESTAURANT1_ID), RESTAURANT1);
    }

    @Test
    public void getNotFound() {
        Assert.assertNull(restaurantRepository.get(123));
    }


    @Test
    public void getAll() {
        assertMatch(restaurantRepository.getAll(), Arrays.asList(RESTAURANT1, RESTAURANT2, RESTAURANT3, RESTAURANT4));
    }
}