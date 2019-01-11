package ru.proshkina.voteforlunch.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.proshkina.voteforlunch.model.Dish;
import ru.proshkina.voteforlunch.service.dish.DishService;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static ru.proshkina.voteforlunch.util.ValidationUtil.assureIdConsistent;
import static ru.proshkina.voteforlunch.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = DishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishController {
    static final String REST_URL = "/api/admin/restaurants";
    private final Logger log = LoggerFactory.getLogger(getClass());

    private DishService service;

    @Autowired
    public DishController(DishService service) {
        this.service = service;
    }

    @GetMapping("/{restaurant_id}/dishes/{id}")
    public Dish get(@PathVariable("id") int id, @PathVariable("restaurant_id") int restaurant_id) {
        log.info("get dish {}", id);
        return service.get(id, restaurant_id);
    }

    @DeleteMapping("/{restaurant_id}/dishes/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id, @PathVariable("restaurant_id") int restaurant_id) {
        log.info("delete restaurant {}", id);
        service.delete(id, restaurant_id);
    }

    @PostMapping(value = "/{restaurant_id}/dishes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> create(@Valid @RequestBody Dish dish, @PathVariable("restaurant_id") int restaurant_id) {
        checkNew(dish);
        Dish created = service.create(dish, restaurant_id);
        int id = created.getId();
        log.info("create dish {}", id);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(id).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{restaurant_id}/dishes/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Dish dish, @PathVariable("id") int id, @PathVariable("restaurant_id") int restaurant_id) {
        assureIdConsistent(dish, id);
        service.update(dish, restaurant_id);
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

    @GetMapping("/{restaurant_id}/dishes")
    public List<Dish> getAllByRestaurant(@PathVariable("restaurant_id") int restaurant_id, @RequestParam(value = "date", required = false) LocalDate date) {
        if (date == null) {
            log.info("get all dishes by restaurant {}", restaurant_id);
            return service.getAllByRestaurant(restaurant_id);
        } else {
            log.info("get all dishes by restaurant {}", restaurant_id);
            return service.getAllByRestaurantAndDate(restaurant_id, date);
        }
    }
}