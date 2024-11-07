package com.hql.todo.service;

import com.hql.entities.Match;

import java.util.List;

public interface MatchService {

    List<Match> findAllByAmountOfStrikers(Integer amount, boolean useCriteria);
    long countMatchPlayers(Integer matchId, boolean useCriteria);
}
