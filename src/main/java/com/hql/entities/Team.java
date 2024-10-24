package com.hql.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "TEAMS")
public class Team implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_id_seq_generator")
    @SequenceGenerator(name = "team_id_seq_generator", sequenceName = "team_id_seq", allocationSize = 1)
    private Long id;

    private String country;

    @OneToMany(mappedBy="team", cascade = CascadeType.ALL, orphanRemoval=true, fetch = FetchType.EAGER)
    private List<Player> players;

    @OneToMany(mappedBy="team", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Coach> coaches;

    // region GETTERS and SETTERS

    public Long getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public Team setCountry(String country) {
        this.country = country;
        return this;
    }

//    public List<Match> getMatches() {
//        return matches;
//    }
//
//    public Team setMatches(List<Match> matches) {
//        this.matches = matches;
//        return this;
//    }

    public List<Player> getPlayers() {
        return players;
    }

    public Team setPlayers(List<Player> players) {
        this.players = players;
        return this;
    }

    public List<Coach> getCoaches() {
        return coaches;
    }

    public Team setCoaches(List<Coach> coaches) {
        this.coaches = coaches;
        return this;
    }

    // endregion
}
