package com.hql.entities;

import com.hql.entities.enums.PositionName;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "PLAYER_MATCH_POSITIONS")
public class PlayerMatchPosition implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_match_position_id_seq_generator")
    @SequenceGenerator(name = "player_match_position_id_seq_generator", sequenceName = "player_match_position_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name="PLAYER_ID", nullable=false, foreignKey = @jakarta.persistence.ForeignKey(name = "FK_PLAYER_PLAYER_MATCH_POSITIONS"))
    private Player player;

    @ManyToOne()
    @JoinColumn(name="MATCH_ID", nullable=false, foreignKey = @jakarta.persistence.ForeignKey(name = "FK_MATCH_PLAYER_MATCH_POSITIONS"))
    private Match match;

    @Enumerated(EnumType.STRING)
    private PositionName position;

    // region GETTERS and SETTERS

    public Long getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    public PlayerMatchPosition setPlayer(Player player) {
        this.player = player;
        return this;
    }

    public Match getMatch() {
        return match;
    }

    public PlayerMatchPosition setMatch(Match match) {
        this.match = match;
        return this;
    }

    public PositionName getPosition() {
        return position;
    }

    public PlayerMatchPosition setPosition(PositionName position) {
        this.position = position;
        return this;
    }


    // endregion
}
