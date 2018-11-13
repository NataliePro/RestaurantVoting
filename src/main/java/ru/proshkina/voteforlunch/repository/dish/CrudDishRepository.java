package ru.proshkina.voteforlunch.repository.dish;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.proshkina.voteforlunch.model.Dish;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudDishRepository extends JpaRepository<Dish, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Dish d WHERE d.id=:id")
    int delete(@Param("id") int id);

    @Override
    Dish save(Dish dish);

    @Override
    Optional<Dish> findById(Integer id);

    List<Dish> findAllByDateOrderByRestaurantIdAscPriceInCentsAsc(@Param("date") LocalDate date);

    List<Dish> findAllByDateAndRestaurant_IdOrderByRestaurantIdAscPriceInCentsAsc(@Param("date") LocalDate date, @Param("restaurant_id") int restaurant_id);
}
