package ru.proshkina.restaurantvoting.to;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

public class RestaurantTo extends BaseTo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;

    private int votesCount;

    private LocalDate date;

    public RestaurantTo() {
    }

    public RestaurantTo(Integer id, String name, int votesCount, LocalDate date) {
        super(id);
        this.name = name;
        this.votesCount = votesCount;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public int getVotesCount() {
        return votesCount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVotesCount(int votesCount) {
        this.votesCount = votesCount;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                "name='" + name + '\'' +
                ", votesCount=" + votesCount +
                ", date=" + date +
                ", id=" + id +
                '}';
    }
}

