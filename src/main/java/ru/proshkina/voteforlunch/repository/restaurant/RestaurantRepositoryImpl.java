package ru.proshkina.voteforlunch.repository.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.proshkina.voteforlunch.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository {
    private static final Sort SORT_ID = new Sort(Sort.Direction.ASC, "id");

    private CrudRestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantRepositoryImpl(CrudRestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        if (!restaurant.isNew() && get(restaurant.getId()) == null) {
            return null;
        }
        return restaurantRepository.save(restaurant);
    }

    @Override
    public boolean delete(int id) {
        return restaurantRepository.delete(id) != 0;
    }

    @Override
    public Restaurant get(int id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    @Override
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll(SORT_ID);
    }

    @Override
    public List<Restaurant> getAllWithDishes(LocalDate date) {
        return restaurantRepository.findAllByDishes_DateOrDishesIsNullOrderById(date);
    }
}
