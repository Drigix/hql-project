package com.hql.utils;

import com.hql.entities.*;
import com.hql.entities.enums.PositionName;
import com.hql.factories.PersonFactory;
import com.hql.factories.StadiumFactory;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataLoader {

    private final EntityManagerFactory FACTORY;

    public DataLoader(EntityManagerFactory FACTORY) {
        this.FACTORY = FACTORY;
    }

    public void loadTeams() {
        try(EntityManager entityManager = FACTORY.createEntityManager()) {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            List<Person> personList = PersonFactory.generatePersons(5);
            personList.forEach(entityManager::persist);

            Team teamA = new Team().setCountry("Poland");
            List<Player> teamAPlayers = PersonFactory.generateTeamPlayers(12, teamA);
            List<Coach> teamACoaches = PersonFactory.generateCoaches(2, teamA);
            teamA.setCoaches(teamACoaches).setPlayers(teamAPlayers);

            Team teamB = new Team().setCountry("Spain");
            List<Player> teamBPlayers = PersonFactory.generateTeamPlayers(12, teamB);
            List<Coach> teamBCoaches = PersonFactory.generateCoaches(2, teamB);
            teamB.setCoaches(teamBCoaches).setPlayers(teamBPlayers);

            Team teamC = new Team().setCountry("England");
            List<Player> teamCPlayers = PersonFactory.generateTeamPlayers(12, teamC);
            List<Coach> teamCCoaches = PersonFactory.generateCoaches(2, teamC);
            teamC.setCoaches(teamCCoaches).setPlayers(teamCPlayers);

            Team teamD = new Team().setCountry("Germany");
            List<Player> teamDPlayers = PersonFactory.generateTeamPlayers(12, teamD);
            List<Coach> teamDCoaches = PersonFactory.generateCoaches(2, teamD);
            teamD.setCoaches(teamDCoaches).setPlayers(teamDPlayers);

            entityManager.persist(teamA);
            entityManager.persist(teamB);
            entityManager.persist(teamC);
            entityManager.persist(teamD);

            transaction.commit();
        }
    }

    public void loadStadiums() {
        try(EntityManager entityManager = FACTORY.createEntityManager()) {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            Stadium stadiumA = StadiumFactory.generateStadium(0);
            Stadium stadiumB = StadiumFactory.generateStadium(1);
            Stadium stadiumC = StadiumFactory.generateStadium(2);
            Stadium stadiumD = StadiumFactory.generateStadium(3);

            entityManager.persist(stadiumA);
            entityManager.persist(stadiumB);
            entityManager.persist(stadiumC);
            entityManager.persist(stadiumD);

            transaction.commit();
        }
    }

    public void playTestMatch() {
        try(EntityManager entityManager = FACTORY.createEntityManager()) {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            Stadium stadiumA = entityManager.find(Stadium.class, 1);
            Team teamA = entityManager.find(Team.class, 1);
            Team teamB = entityManager.find(Team.class, 2);

            List<Player> teamAPlayers = teamA.getPlayers().subList(0, 7);
            List<Player> teamBPlayers = teamB.getPlayers().subList(0, 7);

            Match match = new Match()
                    .setTeamA(teamA)
                    .setTeamB(teamB)
                    .setStadium(stadiumA)
                            .setDate(LocalDateTime.now())
                    .setResult("1-0");
            entityManager.persist(match);

            List<PlayerMatchPosition> players = new ArrayList<>();
            players.add(new PlayerMatchPosition()
                    .setPlayer(teamAPlayers.getFirst())
                    .setMatch(match)
                    .setPosition(PositionName.GOALKEEPER));

            match.setPlayerMatchPositions(players);
            players.add(new PlayerMatchPosition().setPlayer(teamBPlayers.getFirst())
                    .setMatch(match).setPosition(PositionName.GOALKEEPER));

            for(int i = 1; i < 5; i++) {
                players.add(new PlayerMatchPosition().setPlayer(teamAPlayers.get(i))
                        .setMatch(match).setPosition(PositionName.LEFT_MIDFIELDER));
                players.add(new PlayerMatchPosition().setPlayer(teamBPlayers.get(i))
                        .setMatch(match).setPosition(PositionName.LEFT_MIDFIELDER));
            }

            for (int i = 5; i < 7; i++) {
                players.add(new PlayerMatchPosition().setPlayer(teamAPlayers.get(i))
                        .setMatch(match).setPosition(PositionName.LEFT_WING));
                players.add(new PlayerMatchPosition().setPlayer(teamBPlayers.get(i))
                        .setMatch(match).setPosition(PositionName.LEFT_WING));
            }

            transaction.commit();
        }
    }
}
