package ru.proshkina.voteforlunch.service.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.proshkina.voteforlunch.AbstractServiceTest;
import ru.proshkina.voteforlunch.model.Restaurant;
import ru.proshkina.voteforlunch.service.dish.DishTestData;
import ru.proshkina.voteforlunch.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.proshkina.voteforlunch.service.dish.DishTestData.DISH1;
import static ru.proshkina.voteforlunch.service.dish.DishTestData.DISH8;
import static ru.proshkina.voteforlunch.service.restaurant.RestaurantTestData.*;

public class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    private RestaurantService service;

    @Test
    void testCreate() {
        Restaurant newRest = new Restaurant(null, "new Restaurant");
        Restaurant createdRest = service.create(newRest);
        newRest.setId(createdRest.getId());
        assertMatch(service.getAll(), RESTAURANT1, RESTAURANT2, RESTAURANT3, RESTAURANT4, RESTAURANT5, newRest);
    }

    @Test
    void testUpdate() {
        Restaurant updated = new Restaurant(RESTAURANT1_ID, "Updated restaurant");
        service.update(updated);
        assertMatch(service.getAll(), updated, RESTAURANT2, RESTAURANT3, RESTAURANT4, RESTAURANT5);
    }

    @Test
    void testUpdateNotFound() {
        Restaurant updated = new Restaurant(123, "Updated restaurant");
        assertThrows(NotFoundException.class, () ->
                service.update(updated));
    }

    @Test
    void testGet() {
        assertMatch(service.get(RESTAURANT1_ID), RESTAURANT1);
    }

    @Test
    void testGetNotFound() {
        assertThrows(NotFoundException.class, () ->
                service.get(123));
    }

    @Test
    void testDelete() {
        service.delete(RESTAURANT1_ID);
        assertMatch(service.getAll(), RESTAURANT2, RESTAURANT3, RESTAURANT4, RESTAURANT5);
    }

    @Test
    void testDeleteNotFound() {
        assertThrows(NotFoundException.class, () ->
                service.delete(123));
    }


    @Test
    void testGetAll() {
        assertMatch(service.getAll(), RESTAURANT1, RESTAURANT2, RESTAURANT3, RESTAURANT4, RESTAURANT5);
    }

    @Test
    void testGetAllWithDishes() {
        List<Restaurant> allWithDishes = service.getAllWithDishesByDate(TEST_DATE);
        assertMatch(allWithDishes, RESTAURANT1, RESTAURANT2, RESTAURANT3, RESTAURANT4);
        DishTestData.assertMatch(allWithDishes.get(0).getDishes(), DISH1, DISH8);
    }

    @Test
    void testValidation() {
        validateRootCause(() -> service.create(new Restaurant(null, " ")), ConstraintViolationException.class);
    }
}