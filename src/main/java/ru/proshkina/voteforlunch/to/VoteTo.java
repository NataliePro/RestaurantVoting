package ru.proshkina.voteforlunch.to;

import ru.proshkina.voteforlunch.model.Restaraunt;

public class VoteTo {

    private final Restaraunt restaraunt;

    private final int votesCount;

    public VoteTo(Restaraunt restaraunt, int votesCount) {
        this.restaraunt = restaraunt;
        this.votesCount = votesCount;
    }

    public Restaraunt getRestaraunt() {
        return restaraunt;
    }

    public int getVotesCount() {
        return votesCount;
    }
}

