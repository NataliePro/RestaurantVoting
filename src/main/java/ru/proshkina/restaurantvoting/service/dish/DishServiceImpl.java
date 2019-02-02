package ru.proshkina.restaurantvoting.service.dish;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.proshkina.restaurantvoting.model.Dish;
import ru.proshkina.restaurantvoting.repository.CrudDishRepository;
import ru.proshkina.restaurantvoting.repository.CrudRestaurantRepository;

import java.time.LocalDate;
import java.util.List;

import static ru.proshkina.restaurantvoting.util.ValidationUtil.checkNew;
import static ru.proshkina.restaurantvoting.util.ValidationUtil.checkNotFoundWithId;

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
    public Dish create(Dish dish, int restaurantId) {
        Assert.notNull(dish, "dish must not be null");
        checkNew(dish);
        dish.setRestaurant(restaurantRepository.getOne(restaurantId));
        return dishRepository.save(dish);
    }

    @Override
    public Dish get(int id, int restaurantId) {
        Dish dish = dishRepository.findById(id).filter(d -> d.getRestaurant().getId() == restaurantId).orElse(null);
        return checkNotFoundWithId(dish, id);
    }

    @Override
    public void delete(int id, int restaurantId) {
        checkNotFoundWithId(dishRepository.delete(id, restaurantId) != 0, id);
    }

    @Transactional
    @Override
    public void update(Dish dish, int restaurantId) {
        Assert.notNull(dish, "dish must not be null");
        int id = dish.getId();
        checkNotFoundWithId(get(id, restaurantId), id);
        dish.setRestaurant(restaurantRepository.getOne(restaurantId));
        dishRepository.save(dish);
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
    public List<Dish> getAllByRestaurantAndDate(int restaurantId, LocalDate date) {
        Assert.notNull(date, "date must not be null");
        return dishRepository.findAllByDateAndRestaurant_IdOrderByRestaurantIdAscPriceInCentsAsc(date, restaurantId);
    }

    @Override
    public List<Dish> getAllByRestaurant(int restaurantId) {
        return dishRepository.findAllByRestaurant_IdOrderByDateAscRestaurantIdAscPriceInCentsAsc(restaurantId);
    }
}
