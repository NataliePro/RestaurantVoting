package ru.proshkina.restaurantvoting.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.proshkina.restaurantvoting.model.Dish;
import ru.proshkina.restaurantvoting.service.dish.DishService;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static ru.proshkina.restaurantvoting.util.ValidationUtil.assureIdConsistent;
import static ru.proshkina.restaurantvoting.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = DishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishController {
    static final String REST_URL = "/rest/admin/restaurants";
    private final Logger log = LoggerFactory.getLogger(getClass());

    private DishService service;

    @Autowired
    public DishController(DishService service) {
        this.service = service;
    }

    @GetMapping("/{restaurantId}/dishes/{id}")
    public Dish get(@PathVariable("id") int id, @PathVariable("restaurantId") int restaurantId) {
        log.info("get dish {}", id);
        return service.get(id, restaurantId);
    }

    @DeleteMapping("/{restaurantId}/dishes/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id, @PathVariable("restaurantId") int restaurantId) {
        log.info("delete restaurant {}", id);
        service.delete(id, restaurantId);
    }

    @PostMapping(value = "/{restaurantId}/dishes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> create(@Valid @RequestBody Dish dish, @PathVariable("restaurantId") int restaurantId) {
        checkNew(dish);
        Dish created = service.create(dish, restaurantId);
        int id = created.getId();
        log.info("create dish {}", id);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(id).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{restaurantId}/dishes/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Dish dish, @PathVariable("id") int id, @PathVariable("restaurantId") int restaurantId) {
        assureIdConsistent(dish, id);
        service.update(dish, restaurantId);
        log.info("update dish {}", id);
    }

    @GetMapping("/dishes")
    public List<Dish> getAll(@RequestParam(value = "date", required = false) LocalDate date) {
        if (date == null) {
            log.info("get all dishes");
            return service.getAll();
        }
        log.info("get all dishes by date {}" + date);
        return service.getAllForDate(date);
    }

    @GetMapping("/{restaurantId}/dishes")
    public List<Dish> getAllByRestaurant(@PathVariable("restaurantId") int restaurantId, @RequestParam(value = "date", required = false) LocalDate date) {
        if (date == null) {
            log.info("get all dishes by restaurant {}", restaurantId);
            return service.getAllByRestaurant(restaurantId);
        } else {
            log.info("get all dishes by restaurant {}", restaurantId);
            return service.getAllByRestaurantAndDate(restaurantId, date);
        }
    }
}