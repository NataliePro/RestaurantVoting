package ru.proshkina.voteforlunch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Entity
@Table(name = "dishes")
public class Dish extends AbstractNamedEntity {

    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDate date;

    @Column(name = "price", nullable = false, columnDefinition = "int")
    @Range(min = 10, max = 1000000, message = "price in cents must be between 100 and 100000")
    private Integer priceInCents;

    @JsonIgnoreProperties("dishes")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull
    private Restaurant restaurant;

    public Dish() {
    }

    public Dish(Integer id, String name, LocalDate date, Integer priceInCents) {
        super(id, name);
        this.date = date;
        this.priceInCents = priceInCents;
    }

    public Dish(String name, LocalDate date, Integer priceInCents) {
        super(null, name);
        this.date = date;
        this.priceInCents = priceInCents;
    }

    public Dish(Dish dish) {
        this(dish.getId(), dish.getName(), dish.getDate(), dish.getPriceInCents());
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getPriceInCents() {
        return priceInCents;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setPriceInCents(Integer priceInCents) {
        this.priceInCents = priceInCents;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "date=" + date +
                ", priceInCents=" + priceInCents +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
