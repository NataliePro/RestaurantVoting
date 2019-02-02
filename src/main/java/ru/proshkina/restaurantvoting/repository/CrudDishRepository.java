package ru.proshkina.restaurantvoting.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.proshkina.restaurantvoting.model.Dish;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudDishRepository extends JpaRepository<Dish, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Dish d WHERE d.id=:id AND d.restaurant.id=:restaurantId")
    int delete(@Param("id") int id, @Param("restaurantId") int restaurantId);

    @Override
    @Transactional
    Dish save(Dish dish);

    @Override
    Optional<Dish> findById(Integer id);

    @Query("SELECT d FROM Dish d ORDER BY d.date ASC , d.restaurant.id ASC , d.priceInCents ASC ")
    List<Dish> findAll();

    @EntityGraph(attributePaths = {"restaurant"})
    List<Dish> findAllByDateOrderByRestaurantIdAscPriceInCentsAsc(@Param("date") LocalDate date);

    List<Dish> findAllByRestaurant_IdOrderByDateAscRestaurantIdAscPriceInCentsAsc(@Param("restaurantId") int restaurantId);

    List<Dish> findAllByDateAndRestaurant_IdOrderByRestaurantIdAscPriceInCentsAsc(@Param("date") LocalDate date, @Param("restaurantId") int restaurantId);
}
