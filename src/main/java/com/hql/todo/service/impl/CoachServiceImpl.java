package com.hql.todo.service.impl;

import com.hql.todo.dao.CoachDAO;
import com.hql.todo.service.CoachService;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class CoachServiceImpl implements CoachService {

    private CoachDAO coachDAO;

    public CoachServiceImpl(EntityManagerFactory FACTORY) {
        this.coachDAO = new CoachDAO(FACTORY);
    }

    @Override
    public List<String> findAllSecondNamesByTeamId(Long teamId, boolean useCriteria) {
        return  useCriteria ?
                coachDAO.finaAllSecondNamesByTeamCriteria(teamId) :
                coachDAO.finaAllSecondNamesByTeam(teamId);
    }
}
