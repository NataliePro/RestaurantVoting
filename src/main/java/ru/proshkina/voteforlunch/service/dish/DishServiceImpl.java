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

    private DishRepository dishRepository;

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
    public Dish get(int id) {
        return checkNotFoundWithId(dishRepository.get(id), id);
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(dishRepository.delete(id), id);
    }

    @Override
    public void update(Dish dish, int restaurant_id) {
        Assert.notNull(dish, "dish must not be null");
        checkNotNew(dish);
        checkNotFoundWithId(dishRepository.save(dish, restaurant_id), dish.getId());
    }

    @Override
    public List<Dish> getAll(LocalDate date) {
        Assert.notNull(date, "date must not be null");
        return dishRepository.getAll(date);
    }

    @Override
    public List<Dish> getAllByRestaurant(LocalDate date, int restaurant_id) {
        Assert.notNull(date, "date must not be null");
        return dishRepository.getAllByRestaurant(date, restaurant_id);
    }
}
