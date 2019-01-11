package ru.proshkina.voteforlunch.service.dish;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.proshkina.voteforlunch.AbstractServiceTest;
import ru.proshkina.voteforlunch.model.Dish;
import ru.proshkina.voteforlunch.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.proshkina.voteforlunch.service.dish.DishTestData.*;

public class DishServiceTest extends AbstractServiceTest {

    @Autowired
    private DishService service;

    @Test
    void testCreate() {
        Dish newDish = getCreated();
        Dish createdDish = service.create(newDish, RESTAURANT1_ID);
        newDish.setId(createdDish.getId());
        assertMatch(service.getAllByRestaurantAndDate(RESTAURANT1_ID, TEST_DATE), DISH1, newDish, DISH8);
    }

    @Test
    void testGet() {
        assertMatch(service.get(DISH1_ID, RESTAURANT1_ID), DISH1);
    }

    @Test
    void testGetNotFound() {
        assertThrows(NotFoundException.class, () ->
                service.get(54, RESTAURANT1_ID));
    }

    @Test
    void testDelete() {
        service.delete(DISH1_ID, RESTAURANT1_ID);
        assertMatch(service.getAllForDate(TEST_DATE), DISH8, DISH3, DISH6, DISH7, DISH2, DISH4, DISH5, DISH9);
    }

    @Test
    void testDeleteNotFound() {
        assertThrows(NotFoundException.class, () ->
                service.delete(111, RESTAURANT1_ID));
    }

    @Test
    void testUpdate() {
        Dish updated = getUpdated();
        service.update(updated, RESTAURANT1_ID);
        assertMatch(service.get(DISH1_ID, RESTAURANT1_ID), updated);
    }

    @Test
    void testUpdateNotFound() {
        Dish updated = getUpdated();
        updated.setId(574);
        assertThrows(NotFoundException.class, () ->
                service.update(updated, RESTAURANT2_ID));
    }

    @Test
    void testGetAllForDate() {
        assertMatch(service.getAllForDate(TEST_DATE), DISH1, DISH8, DISH3, DISH6, DISH7, DISH2, DISH4, DISH5, DISH9);
    }

    @Test
    void testGetAll() {
        assertMatch(service.getAll(), DISH10, DISH1, DISH8, DISH3, DISH6, DISH7, DISH2, DISH4, DISH5, DISH9);
    }

    @Test
    void testGetAllByRestaurantAndDate() {
        assertMatch(service.getAllByRestaurantAndDate(RESTAURANT1_ID, TEST_DATE), DISH1, DISH8);
    }

    @Test
    void testGetAllByRestaurant() {
        assertMatch(service.getAllByRestaurant(RESTAURANT1_ID), DISH10, DISH1, DISH8);
    }

    @Test
    void testValidation() throws Exception {
        validateRootCause(() -> service.create(new Dish(null, " ", TEST_DATE, 10000), RESTAURANT1_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Dish(null, "Disssh", null, 10000), RESTAURANT1_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Dish(null, "Disssh", TEST_DATE, 1), RESTAURANT1_ID), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Dish(null, "Disssh", TEST_DATE, 10000000), RESTAURANT1_ID), ConstraintViolationException.class);
    }
}