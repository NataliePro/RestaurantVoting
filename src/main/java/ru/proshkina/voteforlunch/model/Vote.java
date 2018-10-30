package ru.proshkina.voteforlunch.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames = {"date","user_id"}, name = "votes_unique_date_user_id_idx")})
public class Vote extends AbstractBaseEntity{

    public Vote() {
    }

    public Vote(Integer id, LocalDate date, LocalTime time, User user, Restaraunt restaraunt) {
        super(id);
        this.date = date;
        this.time = time;
        this.user = user;
        this.restaraunt = restaraunt;
    }

    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDate date;

    @Column(name = "time", nullable = false)
    @NotNull
    private LocalTime time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaraunt_id", nullable = false)
    @NotNull
    private Restaraunt restaraunt;

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public User getUser() {
        return user;
    }

    public Restaraunt getRestaraunt() {
        return restaraunt;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRestaraunt(Restaraunt restaraunt) {
        this.restaraunt = restaraunt;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "date=" + date +
                ", time=" + time +
                ", user=" + user +
                ", restaraunt=" + restaraunt +
                '}';
    }
}
