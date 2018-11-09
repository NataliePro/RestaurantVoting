package ru.proshkina.voteforlunch.service.dish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.proshkina.voteforlunch.model.Dish;
import ru.proshkina.voteforlunch.repository.dish.DishRepository;

import java.time.LocalDate;
import java.util.List;

import static ru.proshkina.voteforlunch.util.ValidationUtil.checkNotFoundWithId;

@Service
public class DishServiceImpl implements DishService {

    private DishRepository repository;

    @Autowired
    public DishServiceImpl(DishRepository repository) {
        this.repository = repository;
    }

    @Override
    public Dish create(Dish dish, int restaurant_id) {
        Assert.notNull(dish, "dish must not be null");
        return repository.save(dish, restaurant_id);
    }

    @Override
    public Dish get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public void update(Dish dish, int restaurant_id) {
        Assert.notNull(dish, "dish must not be null");
        checkNotFoundWithId(repository.save(dish, restaurant_id), dish.getId());
    }

    @Override
    public List<Dish> getAll(LocalDate date) {
        Assert.notNull(date, "date must not be null");
        return repository.getAll(date);
    }

    @Override
    public List<Dish> getAllByRestaurant(LocalDate date, int restaurant_id) {
        Assert.notNull(date, "date must not be null");
        return repository.getAllByRestaurant(date, restaurant_id);
    }
}
