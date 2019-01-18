package ru.proshkina.voteforlunch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {

    @JsonIgnoreProperties("restaurant")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OrderBy("priceInCents ASC ")
    protected Set<Dish> dishes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    protected List<Vote> votes;

    public Restaurant() {
    }

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    public Restaurant(Restaurant restaurant) {
        this(restaurant.getId(), restaurant.getName());
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
