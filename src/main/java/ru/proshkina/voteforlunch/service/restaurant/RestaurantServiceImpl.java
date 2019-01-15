package ru.proshkina.voteforlunch.service.restaurant;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.proshkina.voteforlunch.model.Restaurant;
import ru.proshkina.voteforlunch.model.Vote;
import ru.proshkina.voteforlunch.repository.CrudRestaurantRepository;
import ru.proshkina.voteforlunch.repository.CrudVoteRepository;
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
    private static final Sort SORT_ID = new Sort(Sort.Direction.ASC, "id");

    private final CrudRestaurantRepository restaurantRepository;
    private final CrudVoteRepository voteRepository;

    public RestaurantServiceImpl(CrudRestaurantRepository restaurantRepository, CrudVoteRepository voteRepository) {
        this.restaurantRepository = restaurantRepository;
        this.voteRepository = voteRepository;
    }

    @Override
    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return restaurantRepository.save(restaurant);
    }

    @Transactional
    @Override
    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        int id = restaurant.getId();
        Restaurant checkedRestaurant = checkNotFoundWithId(get(id),id);
        restaurantRepository.save(checkedRestaurant);
    }

    @Override
    public Restaurant get(int id) {
        return checkNotFoundWithId(restaurantRepository.findById(id).orElse(null), id);
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(restaurantRepository.delete(id), id);
    }

    @Override
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll(SORT_ID);
    }

    @Override
    public List<Restaurant> getAllWithDishesByDate(LocalDate date) {
        Assert.notNull(date, "date must not be null");
        return restaurantRepository.findAllByDishes_DateOrderById(date);
    }

    @Transactional
    @Override
    public List<RestaurantTo> getAllWithVotesByDate(LocalDate date) {
        List<Restaurant> restaurantForDateList = restaurantRepository.findAllByDishes_DateOrderById(date);
        List<Vote> voteForDateList = voteRepository.findAllByDateOrderByTimeAsc(date);
        Map<Restaurant, Long> restaurantVoteCountMap = voteForDateList.stream()
                .collect(Collectors.groupingBy(Vote::getRestaurant, Collectors.counting()));
        return restaurantForDateList.stream()
                .map(restaurant -> asTo(restaurant, restaurantVoteCountMap.getOrDefault(restaurant, 0L).intValue(), date))
                .sorted(Comparator.comparingInt(RestaurantTo::getVotesCount).reversed())
                .collect(Collectors.toList());
    }
}
