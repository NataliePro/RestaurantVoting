package ru.proshkina.voteforlunch.service.restaurant;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.proshkina.voteforlunch.AbstractServiceTest;
import ru.proshkina.voteforlunch.model.Restaurant;
import ru.proshkina.voteforlunch.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.List;

import static ru.proshkina.voteforlunch.TestUtil.assertMatch;
import static ru.proshkina.voteforlunch.service.restaurant.RestaurantTestData.*;

public class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    private RestaurantService service;

    @Test
    public void create() {
        Restaurant newRest = new Restaurant(null, "new Restaurant");
        Restaurant createdRest = service.create(newRest);
        newRest.setId(createdRest.getId());
        assertMatch(service.getAll(), List.of(RESTAURANT1, RESTAURANT2, RESTAURANT3, RESTAURANT4, RESTAURANT5, newRest), "dishes", "votes");
    }

    @Test
    public void update() {
        Restaurant updated = new Restaurant(RESTAURANT1_ID, "Updated restaurant");
        service.update(updated);
        assertMatch(service.getAll(), Arrays.asList(updated, RESTAURANT2, RESTAURANT3, RESTAURANT4, RESTAURANT5), "dishes", "votes");
    }

    @Test
    public void updateNotFound() {
        thrown.expect(NotFoundException.class);
        Restaurant updated = new Restaurant(123, "Updated restaurant");
        service.update(updated);
    }

    @Test
    public void get() {
        assertMatch(service.get(RESTAURANT1_ID), RESTAURANT1, "dishes", "votes");
    }

    @Test
    public void getNotFound() {
        thrown.expect(NotFoundException.class);
        service.get(123);
    }

    @Test
    public void delete() {
        service.delete(RESTAURANT1_ID);
        assertMatch(service.getAll(), List.of(RESTAURANT2, RESTAURANT3, RESTAURANT4, RESTAURANT5), "dishes", "votes");
    }

    @Test
    public void deleteNotFound() {
        thrown.expect(NotFoundException.class);
        service.delete(123);
    }


    @Test
    public void getAll() {
        assertMatch(service.getAll(), List.of(RESTAURANT1, RESTAURANT2, RESTAURANT3, RESTAURANT4, RESTAURANT5), "dishes", "votes");
    }

    @Test
    public void getAllWithDishes() {
        List<Restaurant> allWithDishes = service.getAllWithDishes(TEST_DATE);
        assertMatch(allWithDishes, List.of(RESTAURANT1, RESTAURANT2, RESTAURANT3, RESTAURANT4, RESTAURANT5), "dishes", "votes");
        assertMatch(allWithDishes.get(0).getDishes(), List.of(DISH1, DISH8), "restaurant");
    }

    @Test
    public void testValidation() throws Exception {
        validateRootCause(() -> service.create(new Restaurant(null, " ")), ConstraintViolationException.class);
    }
}