package com.hql.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "MATCHES")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "match_id_seq_generator")
    @SequenceGenerator(name = "match_id_seq_generator", sequenceName = "match_id_seq", allocationSize = 1)
    @Column(name = "MATCH_ID")
    private Long id;

    private LocalDateTime date;

    private String result;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="stadium_id", nullable=false, foreignKey = @jakarta.persistence.ForeignKey(name = "FK_STADIUM_MATCH"))
    private Stadium stadium;

    @OneToMany(mappedBy = "match", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<PlayerMatchPosition> playerMatchPositions;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teamA_id", nullable = false, foreignKey = @jakarta.persistence.ForeignKey(name = "FK_TEAM_A_MATCH"))
    private Team teamA;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teamB_id", nullable = false, foreignKey = @jakarta.persistence.ForeignKey(name = "FK_TEAM_B_MATCH"))
    private Team teamB;

    // region GETTERS and SETTERS

    public Long getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Match setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    public String getResult() {
        return result;
    }

    public Match setResult(String result) {
        this.result = result;
        return this;
    }

    public Stadium getStadium() {
        return stadium;
    }

    public Match setStadium(Stadium stadium) {
        this.stadium = stadium;
        return this;
    }

    public Team getTeamA() {
        return teamA;
    }

    public Match setTeamA(Team teamA) {
        this.teamA = teamA;
        return this;
    }

    public Team getTeamB() {
        return teamB;
    }

    public Match setTeamB(Team teamB) {
        this.teamB = teamB;
        return this;
    }

    public List<PlayerMatchPosition> getPlayerMatchPositions() {
        return playerMatchPositions;
    }

    public Match setPlayerMatchPositions(List<PlayerMatchPosition> playerMatchPositions) {
        this.playerMatchPositions = playerMatchPositions;
        return this;
    }

    // endregion
}

