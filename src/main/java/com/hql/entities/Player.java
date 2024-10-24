package com.hql.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "PLAYERS")
@PrimaryKeyJoinColumn(name = "PLAYER_ID", foreignKey = @jakarta.persistence.ForeignKey(name = "FK_PLAYER_PER"))
public class Player extends Person implements Serializable {
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name="TEAM_ID", foreignKey = @jakarta.persistence.ForeignKey(name = "FK_PLAYER_TEAM"))
    private Team team;

    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    @OneToMany(mappedBy="player", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<PlayerMatchPosition> playerMatchPositions;

    public Player() {
    }

    public Player(String secondName, String firstName) {
        super(secondName, firstName);
    }

    public Player(String secondName, String firstName, Team team) {
        super(secondName, firstName);
        this.team = team;
    }

    public Player(String secondName, String firstName, Team team, List<PlayerMatchPosition> playerMatchPositions) {
        super(secondName, firstName);
        this.team = team;
        this.playerMatchPositions = playerMatchPositions;
    }

    // region GETTERS and SETTERS

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    public Team getTeam() {
        return team;
    }

    public Player setTeam(Team team) {
        this.team = team;
        return this;
    }

    public List<PlayerMatchPosition> getPlayerMatchPositions() {
        return playerMatchPositions;
    }

    public Player setPlayerMatchPositions(List<PlayerMatchPosition> playerMatchPositions) {
        this.playerMatchPositions = playerMatchPositions;
        return this;
    }

    // endregion
}
