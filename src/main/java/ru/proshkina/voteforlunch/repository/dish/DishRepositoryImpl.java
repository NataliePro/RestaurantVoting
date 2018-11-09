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
        if (!dish.isNew() && get(dish.getId()) == null) {
            return null;
        }
        dish.setRestaurant(restaurantRepository.getOne(restaurant_id));
        return dishRepository.save(dish);
    }

    @Override
    public boolean delete(int id) {
        return dishRepository.delete(id) != 0;
    }

    @Override
    public Dish get(int id) {
        return dishRepository.findById(id).orElse(null);
    }

    @Override
    public List<Dish> getAll(LocalDate date) {
        return dishRepository.getAllByDate(date);
    }

    @Override
    public List<Dish> getAllByRestaurant(LocalDate date, int restaurant_id) {
        return dishRepository.getAllByDateAndRestaurant(date, restaurant_id);
    }
}
