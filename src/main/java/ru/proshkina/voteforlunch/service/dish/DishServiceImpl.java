package ru.proshkina.voteforlunch.service.dish;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.proshkina.voteforlunch.model.Dish;
import ru.proshkina.voteforlunch.repository.dish.DishRepository;
import ru.proshkina.voteforlunch.repository.restaurant.RestaurantRepository;

import java.time.LocalDate;
import java.util.List;

import static ru.proshkina.voteforlunch.util.ValidationUtil.*;

@Service
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;

    public DishServiceImpl(DishRepository dishRepository, RestaurantRepository restaurantRepository) {
        this.dishRepository = dishRepository;
    }

    @Override
    public Dish create(Dish dish, int restaurant_id) {
        Assert.notNull(dish, "dish must not be null");
        checkNew(dish);
        return checkNotFound(dishRepository.save(dish, restaurant_id), "can't find restaurant with id=" + restaurant_id);
    }

    @Override
    public Dish get(int id, int restaurant_id) {
        return checkNotFoundWithId(dishRepository.get(id, restaurant_id), id);
    }

    @Override
    public void delete(int id, int restaurant_id) {
        checkNotFoundWithId(dishRepository.delete(id, restaurant_id), id);
    }

    @Override
    public void update(Dish dish, int restaurant_id) {
        Assert.notNull(dish, "dish must not be null");
        checkNotFoundWithId(dishRepository.save(dish, restaurant_id), dish.getId());
    }

    @Override
    public List<Dish> getAll() {
        return dishRepository.getAll();
    }

    @Override
    public List<Dish> getAllForDate(LocalDate date) {
        Assert.notNull(date, "date must not be null");
        return dishRepository.getAllByDate(date);
    }

    @Override
    public List<Dish> getAllByRestaurantAndDate(int restaurant_id, LocalDate date) {
        Assert.notNull(date, "date must not be null");
        return dishRepository.getAllByRestaurantAndDate(restaurant_id, date);
    }

    @Override
    public List<Dish> getAllByRestaurant(int restaurant_id) {
        return dishRepository.getAllByRestaurant(restaurant_id);
    }
}
