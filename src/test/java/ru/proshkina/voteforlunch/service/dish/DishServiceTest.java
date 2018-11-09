package ru.proshkina.voteforlunch.service.dish;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.proshkina.voteforlunch.AbstractServiceTest;
import ru.proshkina.voteforlunch.model.Dish;
import ru.proshkina.voteforlunch.util.exception.NotFoundException;

import java.util.List;

import static ru.proshkina.voteforlunch.TestUtil.assertMatch;
import static ru.proshkina.voteforlunch.service.dish.DishTestData.*;

public class DishServiceTest extends AbstractServiceTest {

    @Autowired
    private DishService service;

    @Test
    public void create() {
        Dish newDish = getCreated();
        Dish createdDish = service.create(newDish, RESTAURANT1_ID);
        newDish.setId(createdDish.getId());
        assertMatch(service.getAllByRestaurant(TEST_DATE, RESTAURANT1_ID), List.of(DISH1, newDish, DISH8), "restaurant");
    }

    @Test
    public void get() {
        assertMatch(service.get(DISH1_ID), DISH1, "restaurant");
    }

    @Test
    public void getNotFound() {
        thrown.expect(NotFoundException.class);
        service.get(54);
    }

    @Test
    public void delete() {
        service.delete(DISH1_ID);
        assertMatch(service.getAll(TEST_DATE), List.of(DISH8, DISH3, DISH6, DISH7, DISH2, DISH4, DISH5, DISH9), "restaurant");

    }

    @Test
    public void deleteNotFound() {
        thrown.expect(NotFoundException.class);
        service.delete(111);
    }

    @Test
    public void update() {
        Dish updated = getUpdated();
        service.update(updated, RESTAURANT1_ID);
        assertMatch(service.get(DISH1_ID), updated, "restaurant");
    }

    @Test
    public void updateNotFound() {
        thrown.expect(NotFoundException.class);
        Dish updated = getUpdated();
        updated.setId(574);
        service.update(updated, RESTAURANT2_ID);
    }

    @Test
    public void getAll() {
        assertMatch(service.getAll(TEST_DATE), List.of(DISH1, DISH8, DISH3, DISH6, DISH7, DISH2, DISH4, DISH5, DISH9), "restaurant");
    }

    @Test
    public void getAllByRestaurant() {
        assertMatch(service.getAllByRestaurant(TEST_DATE, RESTAURANT1_ID), List.of(DISH1, DISH8), "restaurant");
    }

}