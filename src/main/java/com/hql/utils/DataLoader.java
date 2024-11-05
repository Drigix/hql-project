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
            Stadium stadiumB = entityManager.find(Stadium.class, 2);
            Stadium stadiumC = entityManager.find(Stadium.class, 3);
            Stadium stadiumD = entityManager.find(Stadium.class, 4);

            Team teamPoland = entityManager.find(Team.class, 1);
            Team teamSpain = entityManager.find(Team.class, 2);
            Team teamEngland = entityManager.find(Team.class, 3);
            Team teamGermany = entityManager.find(Team.class, 4);
            List<Player> teamAPlayers = teamPoland.getPlayers().subList(0, 10);
            List<Player> teamBPlayers = teamSpain.getPlayers().subList(0, 10);
            List<Player> teamCPlayers = teamEngland.getPlayers().subList(0, 10);
            List<Player> teamDPlayers = teamGermany.getPlayers().subList(0, 10);

            Match match = new Match()
                    .setTeamA(teamPoland)
                    .setTeamB(teamSpain)
                    .setStadium(stadiumA)
                            .setDate(LocalDateTime.now())
                    .setResult("1-0");
            entityManager.persist(match);

            List<PlayerMatchPosition> playersMatch = new ArrayList<>();

            playersMatch.add(new PlayerMatchPosition()
                    .setPlayer(teamAPlayers.getFirst())
                    .setMatch(match)
                    .setPosition(PositionName.GOALKEEPER));

            playersMatch.add(new PlayerMatchPosition().setPlayer(teamBPlayers.getFirst())
                    .setMatch(match).setPosition(PositionName.GOALKEEPER));
            loadPlayerMatchPosition(match, playersMatch, teamAPlayers, 1, 3, PositionName.LEFT_MIDFIELDER);
            loadPlayerMatchPosition(match, playersMatch, teamAPlayers, 3, 5, PositionName.LEFT_BACK);
            loadPlayerMatchPosition(match, playersMatch, teamAPlayers, 5, 7, PositionName.LEFT_WING);
            loadPlayerMatchPosition(match, playersMatch, teamAPlayers, 7, 10, PositionName.STRIKER);

            loadPlayerMatchPosition(match, playersMatch, teamBPlayers, 1, 2, PositionName.LEFT_MIDFIELDER);
            loadPlayerMatchPosition(match, playersMatch, teamBPlayers, 2, 5, PositionName.LEFT_BACK);
            loadPlayerMatchPosition(match, playersMatch, teamBPlayers, 5, 8, PositionName.LEFT_WING);
            loadPlayerMatchPosition(match, playersMatch, teamBPlayers, 8, 10, PositionName.STRIKER);
            match.setPlayerMatchPositions(playersMatch);


            Match match2 = new Match()
                    .setTeamA(teamEngland)
                    .setTeamB(teamGermany)
                    .setStadium(stadiumB)
                    .setDate(LocalDateTime.now())
                    .setResult("2-2");
            entityManager.persist(match2);

            List<PlayerMatchPosition> playersMatch2 = new ArrayList<>();

            playersMatch2.add(new PlayerMatchPosition()
                    .setPlayer(teamCPlayers.getFirst())
                    .setMatch(match2)
                    .setPosition(PositionName.GOALKEEPER));

            playersMatch2.add(new PlayerMatchPosition().setPlayer(teamDPlayers.getFirst())
                    .setMatch(match2).setPosition(PositionName.GOALKEEPER));

            loadPlayerMatchPosition(match2, playersMatch2, teamCPlayers, 1, 4, PositionName.LEFT_MIDFIELDER);
            loadPlayerMatchPosition(match2, playersMatch2, teamCPlayers, 4, 7, PositionName.LEFT_BACK);
            loadPlayerMatchPosition(match2, playersMatch2, teamCPlayers, 7, 9, PositionName.LEFT_WING);
            loadPlayerMatchPosition(match2, playersMatch2, teamCPlayers, 9, 10, PositionName.STRIKER);

            loadPlayerMatchPosition(match2, playersMatch2, teamDPlayers, 1, 2, PositionName.LEFT_MIDFIELDER);
            loadPlayerMatchPosition(match2, playersMatch2, teamDPlayers, 2, 4, PositionName.LEFT_BACK);
            loadPlayerMatchPosition(match2, playersMatch2, teamDPlayers, 4, 6, PositionName.LEFT_WING);
            loadPlayerMatchPosition(match2, playersMatch2, teamDPlayers, 6, 10, PositionName.STRIKER);

            match2.setPlayerMatchPositions(playersMatch2);

            Match match3 = new Match()
                    .setTeamA(teamPoland)
                    .setTeamB(teamEngland)
                    .setStadium(stadiumC)
                    .setDate(LocalDateTime.now())
                    .setResult("2-4");
            entityManager.persist(match3);

            List<PlayerMatchPosition> playersMatch3 = new ArrayList<>();

            playersMatch3.add(new PlayerMatchPosition()
                    .setPlayer(teamAPlayers.getFirst())
                    .setMatch(match3)
                    .setPosition(PositionName.GOALKEEPER));

            playersMatch3.add(new PlayerMatchPosition().setPlayer(teamCPlayers.getFirst())
                    .setMatch(match3).setPosition(PositionName.GOALKEEPER));

            loadPlayerMatchPosition(match3, playersMatch3, teamAPlayers, 1, 3, PositionName.LEFT_MIDFIELDER);
            loadPlayerMatchPosition(match3, playersMatch3, teamAPlayers, 3, 6, PositionName.LEFT_BACK);
            loadPlayerMatchPosition(match3, playersMatch3, teamAPlayers, 6, 9, PositionName.LEFT_WING);
            loadPlayerMatchPosition(match3, playersMatch3, teamAPlayers, 9, 10, PositionName.STRIKER);

            loadPlayerMatchPosition(match3, playersMatch3, teamCPlayers, 1, 3, PositionName.LEFT_MIDFIELDER);
            loadPlayerMatchPosition(match3, playersMatch3, teamCPlayers, 3, 5, PositionName.LEFT_BACK);
            loadPlayerMatchPosition(match3, playersMatch3, teamCPlayers, 5, 7, PositionName.LEFT_WING);
            loadPlayerMatchPosition(match3, playersMatch3, teamCPlayers, 7, 10, PositionName.STRIKER);

            match3.setPlayerMatchPositions(playersMatch3);


            Match match4 = new Match()
                    .setTeamA(teamSpain)
                    .setTeamB(teamGermany)
                    .setStadium(stadiumD)
                    .setDate(LocalDateTime.now())
                    .setResult("5-3");
            entityManager.persist(match4);

            List<PlayerMatchPosition> playersMatch4 = new ArrayList<>();

            playersMatch4.add(new PlayerMatchPosition()
                    .setPlayer(teamBPlayers.getFirst())
                    .setMatch(match4)
                    .setPosition(PositionName.GOALKEEPER));

            playersMatch4.add(new PlayerMatchPosition().setPlayer(teamDPlayers.getFirst())
                    .setMatch(match4).setPosition(PositionName.GOALKEEPER));

            loadPlayerMatchPosition(match4, playersMatch4, teamBPlayers, 1, 3, PositionName.LEFT_MIDFIELDER);
            loadPlayerMatchPosition(match4, playersMatch4, teamBPlayers, 3, 6, PositionName.LEFT_BACK);
            loadPlayerMatchPosition(match4, playersMatch4, teamBPlayers, 6, 9, PositionName.LEFT_WING);
            loadPlayerMatchPosition(match4, playersMatch4, teamBPlayers, 9, 10, PositionName.STRIKER);

            loadPlayerMatchPosition(match4, playersMatch4, teamDPlayers, 1, 3, PositionName.LEFT_MIDFIELDER);
            loadPlayerMatchPosition(match4, playersMatch4, teamDPlayers, 3, 5, PositionName.LEFT_BACK);
            loadPlayerMatchPosition(match4, playersMatch4, teamDPlayers, 5, 7, PositionName.LEFT_WING);
            loadPlayerMatchPosition(match4, playersMatch4, teamDPlayers, 7, 10, PositionName.STRIKER);

            match4.setPlayerMatchPositions(playersMatch4);

            transaction.commit();
        }
    }

    public void loadPlayerMatchPosition(
            Match match,
            List<PlayerMatchPosition> players,
            List<Player> teamPlayers,
            Integer startIndex,
            Integer endIndex,
            PositionName positionName) {
        for(int i = startIndex; i < endIndex; i++) {
            players.add(new PlayerMatchPosition().setPlayer(teamPlayers.get(i))
                    .setMatch(match).setPosition(positionName));
        }
    }
}
