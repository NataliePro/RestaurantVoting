package ru.proshkina.restaurantvoting.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.proshkina.restaurantvoting.AuthorizedUser;
import ru.proshkina.restaurantvoting.model.Restaurant;
import ru.proshkina.restaurantvoting.model.User;
import ru.proshkina.restaurantvoting.model.Vote;
import ru.proshkina.restaurantvoting.service.restaurant.RestaurantService;
import ru.proshkina.restaurantvoting.service.vote.VoteService;
import ru.proshkina.restaurantvoting.to.RestaurantTo;
import ru.proshkina.restaurantvoting.to.UserTo;
import ru.proshkina.restaurantvoting.util.UserUtil;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = ProfileController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileController extends AbstractUserController {
    static final String REST_URL = "/rest/profile";

    @Autowired
    VoteService voteService;

    @Autowired
    RestaurantService restaurantService;

    public ProfileController() {
    }

    @GetMapping
    public User get(@AuthenticationPrincipal AuthorizedUser authUser) {
        return super.get(authUser.getId());
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal AuthorizedUser authUser) {
        super.delete(authUser.getId());
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<User> register(@Valid @RequestBody UserTo userTo) {
        User created = super.create(UserUtil.createNewFromTo(userTo));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody UserTo userTo, @AuthenticationPrincipal AuthorizedUser authUser) {
        super.update(userTo, authUser.getId());
    }

    @PostMapping(value = "/restaurants/{restaurantId}/votes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> makeVote(@Valid @RequestBody Vote vote, @AuthenticationPrincipal AuthorizedUser authUser, @PathVariable("restaurantId") int restaurantId) {

        Vote created = voteService.createOrUpdate(vote, authUser.getId(), restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/restaurants/{restaurantId}/votes")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping(value = "/restaurants/votes", params = {"date"})
    public List<RestaurantTo> getVotesResultsForDate(@RequestParam("date") LocalDate date) {
        return restaurantService.getAllWithVotesByDate(date);
    }

    @GetMapping(value = "/restaurants/dishes", params = {"date"})
    public List<Restaurant> getAllRestaurantsWithDishesForDate(@AuthenticationPrincipal AuthorizedUser authUser, @RequestParam("date") LocalDate date) {
        return restaurantService.getAllWithDishesByDate(date);
    }
}