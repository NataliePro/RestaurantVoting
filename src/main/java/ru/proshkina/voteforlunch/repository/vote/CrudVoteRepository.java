package ru.proshkina.voteforlunch.repository.vote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.proshkina.voteforlunch.model.Vote;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {

    @Override
    Vote save(Vote vote);

    @Query("SELECT v FROM Vote v JOIN FETCH v.user JOIN FETCH v.restaurant WHERE v.date=:date AND v.user.id=:user_id")
    Vote findByDateAndUser(@Param("date") LocalDate date, @Param("user_id") Integer user_id);

    @Query("SELECT v FROM Vote v JOIN FETCH v.user JOIN FETCH v.restaurant WHERE v.date=:date ORDER BY v.date, v.time ")
    List<Vote> findAllByDate(@Param("date") LocalDate date);

    @Transactional
    @Modifying
    @Query("DELETE FROM Vote WHERE id=:id")
    int delete(@Param("id") int id);

    @Override
    Optional<Vote> findById(Integer id);
}
