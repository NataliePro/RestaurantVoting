package ru.proshkina.voteforlunch.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "dishes")
public class Dish extends AbstractNamedEntity {

    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDate date;

    @Column(name = "price", nullable = false, columnDefinition = "int")
    @Range(min = 0, max = 1000000)
    private Integer priceInCents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaraunt_id", nullable = false)
    @NotNull
    private Restaraunt restaraunt;

    public Dish() {
    }

    public Dish(Integer id, String name, LocalDate date, Integer priceInCents, Restaraunt restaraunt) {
        super(id, name);
        this.date = date;
        this.priceInCents = priceInCents;
        this.restaraunt = restaraunt;
    }

    public Dish(String name, LocalDate date, Integer priceInCents, Restaraunt restaraunt) {
        super(null, name);
        this.date = date;
        this.priceInCents = priceInCents;
        this.restaraunt = restaraunt;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getPriceInCents() {
        return priceInCents;
    }

    public Restaraunt getRestaraunt() {
        return restaraunt;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setPriceInCents(Integer priceInCents) {
        this.priceInCents = priceInCents;
    }

    public void setRestaraunt(Restaraunt restaraunt) {
        this.restaraunt = restaraunt;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "date=" + date +
                ", priceInCents=" + priceInCents +
                ", restaraunt=" + restaraunt +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
