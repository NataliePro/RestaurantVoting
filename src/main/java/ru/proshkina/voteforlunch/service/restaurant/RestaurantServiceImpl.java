package ru.proshkina.voteforlunch.service.restaurant;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.proshkina.voteforlunch.model.Restaurant;
import ru.proshkina.voteforlunch.repository.restaurant.RestaurantRepository;

import java.util.List;

import static ru.proshkina.voteforlunch.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private RestaurantRepository repository;

    public RestaurantServiceImpl(RestaurantRepository repository) {
        this.repository = repository;
    }

    @Override
    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }

    @Override
    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNotFoundWithId(repository.save(restaurant), restaurant.getId());
    }

    @Override
    public Restaurant get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public List<Restaurant> getAll() {
        return repository.getAll();
    }
}
