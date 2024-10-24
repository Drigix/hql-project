package com.hql.factories;

import com.hql.entities.Coach;
import com.hql.entities.Person;
import com.hql.entities.Player;
import com.hql.entities.Team;

import java.util.ArrayList;
import java.util.List;

public class PersonFactory {
    private static final String PLAYER = "PLAYER_";
    private static final String COACH = "COACH_";
    private static final String PERSON = "PERSON_";

    public static List<Player> generateTeamPlayers(int numOfPlayers, Team team) {
        List<Player> players = new ArrayList<Player>();
        for (int i = 0; i < numOfPlayers; i++) {
            players.add(new Player(PLAYER + i, PLAYER + i, team));
        }
        return players;
    }

    public static List<Person> generatePersons(int numOfPersons) {
        List<Person> personList = new ArrayList<>();
        for (int i = 0; i < numOfPersons; i++) {
            personList.add(new Person(PERSON + i, PERSON + i));
        }
        return personList;
    }

    public static List<Coach> generateCoaches(int numOfCoaches, Team team) {
        List<Coach> coachList = new ArrayList<>();
        for (int i = 0; i < numOfCoaches; i++) {
            coachList.add(new Coach(COACH + i, COACH + i, team));
        }
        return coachList;
    }
}
