package ru.proshkina.voteforlunch.to;

import ru.proshkina.voteforlunch.model.Restaurant;

public class VoteTo {

    private final Restaurant restaurant;

    private final int votesCount;

    public VoteTo(Restaurant restaurant, int votesCount) {
        this.restaurant = restaurant;
        this.votesCount = votesCount;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public int getVotesCount() {
        return votesCount;
    }
}

