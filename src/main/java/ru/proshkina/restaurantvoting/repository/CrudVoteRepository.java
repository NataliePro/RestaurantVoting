package ru.proshkina.restaurantvoting.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.proshkina.restaurantvoting.model.Vote;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {

    @Override
    @Transactional
    Vote save(Vote vote);

    @EntityGraph(attributePaths = {"user", "restaurant"})
    Vote findAllByDateAndUser_Id(@Param("date") LocalDate date, @Param("userId") int userId);

    @EntityGraph(attributePaths = {"user", "restaurant"})
    List<Vote> findAllByUser_IdOrderByDate(@Param("userId") int userId);

    @EntityGraph(attributePaths = {"user", "restaurant"})
    @Query("SELECT v FROM Vote v ORDER BY v.date , v.time ASC ")
    List<Vote> getAll();

    @EntityGraph(attributePaths = {"user", "restaurant"})
    List<Vote> findAllByDateOrderByTimeAsc(@Param("date") LocalDate date);

    @Transactional
    @Modifying
    @Query("DELETE FROM Vote WHERE id=:id AND user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Override
    Optional<Vote> findById(Integer id);

    List<Vote> findAllByRestaurant_IdOrderByDateAscIdAsc(@Param("restaurantId") int restaurantId);

    List<Vote> findAllByRestaurant_IdAndUser_IdOrderByDateAscIdAsc(@Param("restaurantId") int restaurantId, @Param("userId") int userId);

    List<Vote> findAllByRestaurant_IdAndDateOrderByDateAscIdAsc(@Param("restaurantId") int restaurantId, @Param("date") LocalDate date);

    Vote findAllByRestaurant_IdAndDateAndUser_Id(@Param("restaurantId") int restaurantId, @Param("date") LocalDate date, @Param("userId") int userId);
}
