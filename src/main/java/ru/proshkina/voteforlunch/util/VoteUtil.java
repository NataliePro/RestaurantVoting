package ru.proshkina.voteforlunch.util;

import ru.proshkina.voteforlunch.model.Vote;
import ru.proshkina.voteforlunch.to.VoteTo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class VoteUtil {


    public static List<VoteTo> getVoteToList(List<Vote> votes) {
        if (votes == null) {
            return Collections.emptyList();
        }
        List<VoteTo> voteToList = new ArrayList<>();
        votes.stream()
                .collect(Collectors.groupingBy(Vote::getRestaurant, Collectors.counting()))
                .forEach((k, v) -> voteToList.add(new VoteTo(k, v.intValue())));
        return voteToList;
    }

}
