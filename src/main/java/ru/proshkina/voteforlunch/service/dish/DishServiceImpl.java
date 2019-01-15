package ru.proshkina.voteforlunch.service.dish;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.proshkina.voteforlunch.model.Dish;
import ru.proshkina.voteforlunch.repository.CrudDishRepository;
import ru.proshkina.voteforlunch.repository.CrudRestaurantRepository;

import java.time.LocalDate;
import java.util.List;

import static ru.proshkina.voteforlunch.util.ValidationUtil.checkNew;
import static ru.proshkina.voteforlunch.util.ValidationUtil.checkNotFoundWithId;

@Service
public class DishServiceImpl implements DishService {

    private final CrudDishRepository dishRepository;
    private final CrudRestaurantRepository restaurantRepository;

    public DishServiceImpl(CrudDishRepository dishRepository, CrudRestaurantRepository restaurantRepository) {
        this.dishRepository = dishRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Transactional
    @Override
    public Dish create(Dish dish, int restaurant_id) {
        Assert.notNull(dish, "dish must not be null");
        checkNew(dish);
        dish.setRestaurant(restaurantRepository.getOne(restaurant_id));
        return dishRepository.save(dish);
    }

    @Override
    public Dish get(int id, int restaurant_id) {
        Dish dish = dishRepository.findById(id).filter(d -> d.getRestaurant().getId() == restaurant_id).orElse(null);
        return checkNotFoundWithId(dish, id);
    }

    @Override
    public void delete(int id, int restaurant_id) {
        checkNotFoundWithId(dishRepository.delete(id, restaurant_id), id);
    }

    @Transactional
    @Override
    public void update(Dish dish, int restaurant_id) {
        Assert.notNull(dish, "dish must not be null");
        int id = dish.getId();
        Dish checkedDish = checkNotFoundWithId(get(id, restaurant_id), id);
        checkedDish.setRestaurant(restaurantRepository.getOne(restaurant_id));
        dishRepository.save(checkedDish);
    }

    @Override
    public List<Dish> getAll() {
        return dishRepository.findAll();
    }

    @Override
    public List<Dish> getAllForDate(LocalDate date) {
        Assert.notNull(date, "date must not be null");
        return dishRepository.findAllByDateOrderByRestaurantIdAscPriceInCentsAsc(date);
    }

    @Override
    public List<Dish> getAllByRestaurantAndDate(int restaurant_id, LocalDate date) {
        Assert.notNull(date, "date must not be null");
        return dishRepository.findAllByDateAndRestaurant_IdOrderByRestaurantIdAscPriceInCentsAsc(date, restaurant_id);
    }

    @Override
    public List<Dish> getAllByRestaurant(int restaurant_id) {
        return dishRepository.findAllByRestaurant_IdOrderByDateAscRestaurantIdAscPriceInCentsAsc(restaurant_id);
    }
}
