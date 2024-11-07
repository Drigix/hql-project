package com.hql.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "COACHES")
@PrimaryKeyJoinColumn(name = "COACH_ID", foreignKey = @jakarta.persistence.ForeignKey(name = "FK_COACH_PER"))
public class Coach extends Person implements Serializable {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="TEAM_ID", foreignKey = @jakarta.persistence.ForeignKey(name = "FK_TEAM_COACH"))
    private Team team;

    public Coach() {
    }

    public Coach(Team team) {
        this.team = team;
    }

    public Coach(String secondName, String firstName) {
        super(secondName, firstName);
    }

    public Coach(String secondName, String firstName, Team team) {
        super(secondName, firstName);
        this.team = team;
    }

    // region GETTERS and SETTERS

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    // endregion
}
