package ru.proshkina.voteforlunch.service.restaurant;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.proshkina.voteforlunch.model.Restaurant;
import ru.proshkina.voteforlunch.model.Vote;
import ru.proshkina.voteforlunch.repository.dish.DishRepository;
import ru.proshkina.voteforlunch.repository.restaurant.RestaurantRepository;
import ru.proshkina.voteforlunch.repository.vote.VoteRepository;
import ru.proshkina.voteforlunch.to.RestaurantTo;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ru.proshkina.voteforlunch.util.RestaurantUtil.asTo;
import static ru.proshkina.voteforlunch.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    private final DishRepository dishRepository;

    private final VoteRepository voteRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, DishRepository dishRepository, VoteRepository voteRepository) {
        this.restaurantRepository = restaurantRepository;
        this.dishRepository = dishRepository;
        this.voteRepository = voteRepository;
    }

    @Override
    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNotFoundWithId(restaurantRepository.save(restaurant), restaurant.getId());
    }

    @Override
    public Restaurant get(int id) {
        return checkNotFoundWithId(restaurantRepository.get(id), id);
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(restaurantRepository.delete(id), id);
    }

    @Override
    public List<Restaurant> getAll() {
        return restaurantRepository.getAll();
    }

    @Override
    public List<Restaurant> getAllWithDishesByDate(LocalDate date) {
        Assert.notNull(date, "date must not be null");
        return restaurantRepository.getAllWithDishes(date);
    }

    @Transactional
    @Override
    public List<RestaurantTo> getAllWithVotesByDate(LocalDate date) {
        List<Restaurant> restaurantList = restaurantRepository.getAllWithDishes(date);
        List<Vote> voteWithRestaurantList = voteRepository.getAllByDate(date);
        Map<Restaurant, Long> restaurantVoteCountMap = voteWithRestaurantList.stream()
                .collect(Collectors.groupingBy(Vote::getRestaurant, Collectors.counting()));
        return restaurantList.stream()
                .map(restaurant -> asTo(restaurant, restaurantVoteCountMap.getOrDefault(restaurant, 0L).intValue(), date))
                .sorted(Comparator.comparingInt(RestaurantTo::getVotesCount).reversed())
                .collect(Collectors.toList());
    }
}
