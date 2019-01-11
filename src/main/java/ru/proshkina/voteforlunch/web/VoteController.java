package ru.proshkina.voteforlunch.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.proshkina.voteforlunch.model.Vote;
import ru.proshkina.voteforlunch.service.vote.VoteService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {
    static final String REST_URL = "/api/admin/votes";
    private final Logger log = LoggerFactory.getLogger(getClass());

    private VoteService service;

    @Autowired
    public VoteController(VoteService service) {
        this.service = service;
    }

    @GetMapping()
    public List<Vote> getAll() {
        log.info("get all votes ");
        return service.getAll();
    }

    @GetMapping(params = {"user", "date"})
    public Vote getByUserAndDate(@RequestParam("user") int user_id, @RequestParam("date") LocalDate date) {
        log.info("get vote by user {} for date {}", user_id, date);
        return service.getByUserAndDate(user_id, date);
    }

    @GetMapping(params = {"date"})
    public List<Vote> getAllByDate(@RequestParam("date") LocalDate date) {
        log.info("get all votes for date {}", date);
        return service.getAllByDate(date);
    }

    @GetMapping(params = {"restaurant"})
    public List<Vote> getAllByRestaurant(@RequestParam("restaurant") int restaurant_id) {
        log.info("get all votes for restaurant {}", restaurant_id);
        return service.getAllByRestaurant(restaurant_id);
    }

    @GetMapping(params = {"restaurant", "user"})
    public List<Vote> getAllByRestaurantAndUser(@RequestParam("restaurant") int restaurant_id, @RequestParam("user") int user_id) {
        log.info("get all votes by restaurant {} and user {}", restaurant_id, user_id);
        return service.getAllByRestaurantAndUser(restaurant_id, user_id);
    }

    @GetMapping(params = {"restaurant", "date"})
    public List<Vote> getAllByRestaurantAndDate(@RequestParam("restaurant") int restaurant_id, @RequestParam("date") LocalDate date) {
        log.info("get all votes by restaurant {} and date {}", restaurant_id, date);
        return service.getAllByRestaurantAndDate(restaurant_id, date);
    }

    @GetMapping(params = {"restaurant", "date", "user"})
    public Vote getByRestaurantDateUser(@RequestParam(value = "restaurant") int restaurant_id,
                                        @RequestParam(value = "date") LocalDate date,
                                        @RequestParam(value = "user") int user_id) {

        log.info("get vote by user {} and restaurant {} and date {}", user_id, restaurant_id, date);
        return service.getByRestaurantAndDateAndUser(restaurant_id, date, user_id);
    }

    @GetMapping(params = {"user"})
    public List<Vote> getAllByUser(@RequestParam("user") int user_id) {
        log.info("get all votes by user {}", user_id);
        return service.getAllByUser(user_id);
    }

}
