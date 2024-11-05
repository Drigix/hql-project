package com.hql;

import com.hql.entities.Match;
import com.hql.entities.Person;
import com.hql.entities.Player;
import com.hql.todo.service.CoachService;
import com.hql.todo.service.MatchService;
import com.hql.todo.service.impl.CoachServiceImpl;
import com.hql.todo.service.impl.MatchServiceImpl;
import com.hql.utils.DataLoader;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    static final Map<String, String> props = new HashMap<>();

    static {
        props.put("jakarta.persistence.schema-generation.database.action", "drop-and-create");
    }
    private final static EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("footballPU", props);

    public static void main(String[] args) {
        DataLoader dataLoader = new DataLoader(FACTORY);
        dataLoader.loadTeams();
        dataLoader.loadStadiums();
        dataLoader.playTestMatch();

        MatchService matchService = new MatchServiceImpl();
        List<Match> matchesWith6Strikers = matchService.findAllByAmountOfStrikers(5, true);
        for(Match match: matchesWith6Strikers) {
            System.out.println("Match with at least 5 strikers: " + match.getTeamA().getCountry() + " vs " + match.getTeamB().getCountry());
        }

        CoachService coachService = new CoachServiceImpl();
        List<String> coachSecondNames = coachService.findAllSecondNamesByTeamId(1L, true);
        for(String secondName: coachSecondNames) {
            System.out.println("Team 1 coach: " + secondName);
        }
    }
}