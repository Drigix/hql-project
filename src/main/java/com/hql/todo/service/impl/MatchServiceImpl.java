package com.hql.todo.service.impl;

import com.hql.entities.Match;
import com.hql.todo.dao.MatchDAO;
import com.hql.todo.service.MatchService;

import java.util.List;

public class MatchServiceImpl implements MatchService {

    private MatchDAO matchDAO;

    public MatchServiceImpl() {
        this.matchDAO = new MatchDAO();
    }

    @Override
    public List<Match> findAllByAmountOfStrikers(Integer amount, boolean useCriteria) {
        return useCriteria ?
                matchDAO.findAllWithAmountStrikersCriteria(amount) :
                matchDAO.findAllWithAmountStrikers(amount);
    }
}