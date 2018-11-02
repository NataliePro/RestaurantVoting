package ru.proshkina.voteforlunch.repository.dish;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.proshkina.voteforlunch.model.Dish;
import ru.proshkina.voteforlunch.repository.AbstractRepositoryTest;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static ru.proshkina.voteforlunch.repository.TestUtil.assertMatch;
import static ru.proshkina.voteforlunch.repository.dish.DishTestData.*;

public class DishRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private DishRepository repository;

    @Test
    public void save() {
        Dish newDish = new Dish(null, "Блюдо111", LocalDate.of(2018, 10, 26), 21000);
        Dish createdDish = repository.save(newDish, RESTAURANT1_ID);
        newDish.setId(createdDish.getId());
        assertMatch(repository.getAllByRestaraunt(TEST_DATE, RESTAURANT1_ID), Arrays.asList(DISH1, newDish, DISH8), "restaurant");
    }

    @Test
    public void delete() {
        repository.delete(DISH1_ID);
        assertMatch(repository.getAll(TEST_DATE), Arrays.asList(DISH8, DISH3, DISH6, DISH7, DISH2, DISH4, DISH5, DISH9), "restaurant");
    }

    @Test
    public void deleteNotFound() {
        assertFalse(repository.delete(111));
    }

    @Test
    public void get() {
        assertMatch(repository.get(DISH1_ID), DISH1, "restaurant");
    }

    @Test
    public void getNotFound() {
        assertNull(repository.get(111));
    }

    @Test
    public void getAll() {
        assertMatch(repository.getAll(TEST_DATE), Arrays.asList(DISH1, DISH8, DISH3, DISH6, DISH7, DISH2, DISH4, DISH5, DISH9), "restaurant");
    }

    @Test
    public void getAllByRestaraunt() {
        assertMatch(repository.getAllByRestaraunt(TEST_DATE, RESTAURANT1_ID), Arrays.asList(DISH1, DISH8), "restaurant");
    }
}