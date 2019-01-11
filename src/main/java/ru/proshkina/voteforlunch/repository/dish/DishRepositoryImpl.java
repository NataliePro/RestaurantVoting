package ru.proshkina.voteforlunch.repository.dish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.proshkina.voteforlunch.model.Dish;
import ru.proshkina.voteforlunch.repository.restaurant.CrudRestaurantRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DishRepositoryImpl implements DishRepository {

    private CrudDishRepository dishRepository;

    private CrudRestaurantRepository restaurantRepository;

    @Autowired
    public DishRepositoryImpl(CrudDishRepository dishRepository, CrudRestaurantRepository restaurantRepository) {
        this.dishRepository = dishRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    @Transactional
    public Dish save(Dish dish, int restaurant_id) {
        if (!dish.isNew() && get(dish.getId(), restaurant_id) == null) {
            return null;
        }
        dish.setRestaurant(restaurantRepository.getOne(restaurant_id));
        return dishRepository.save(dish);
    }

    @Override
    public boolean delete(int id, int restaurant_id) {
        return dishRepository.delete(id, restaurant_id) != 0;
    }

    @Override
    public Dish get(int id, int restaurant_id) {
        return dishRepository.findById(id).filter(dish -> dish.getRestaurant().getId() == restaurant_id).orElse(null);
    }

    @Override
    public List<Dish> getAll() {
        return dishRepository.findAll();
    }

    @Override
    public List<Dish> getAllByDate(LocalDate date) {
        return dishRepository.findAllByDateOrderByRestaurantIdAscPriceInCentsAsc(date);
    }

    @Override
    public List<Dish> getAllByRestaurantAndDate(int restaurant_id, LocalDate date) {
        return dishRepository.findAllByDateAndRestaurant_IdOrderByRestaurantIdAscPriceInCentsAsc(date, restaurant_id);
    }

    @Override
    public List<Dish> getAllByRestaurant(int restaurant_id) {
        return dishRepository.findAllByRestaurant_IdOrderByDateAscRestaurantIdAscPriceInCentsAsc(restaurant_id);
    }
}
