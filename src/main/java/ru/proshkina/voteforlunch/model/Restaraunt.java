package ru.proshkina.voteforlunch.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "restaraunts")
public class Restaraunt extends AbstractNamedEntity {

    public Restaraunt() {
    }

    public Restaraunt(Integer id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return "Restaraunt{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
