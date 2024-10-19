package com.hql.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "player_match_position")
@Getter
@Setter
public class PlayerMatchPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_match_position_id_seq_generator")
    @SequenceGenerator(name = "player_match_position_id_seq_generator", sequenceName = "player_match_position_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name="stadium_id", nullable=false)
    private Player player;

    @ManyToOne
    @JoinColumn(name="stadium_id", nullable=false)
    private Match match;

    private PositionName position;
}
