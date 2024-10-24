package com.hql.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "player_match_position")
@SuperBuilder
@Getter
@Setter
public class PlayerMatchPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_match_position_id_seq_generator")
    @SequenceGenerator(name = "player_match_position_id_seq_generator", sequenceName = "player_match_position_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name="player_id", nullable=false)
    private Player player;

    @ManyToOne
    @JoinColumn(name="match_id", nullable=false)
    private Match match;

    @Enumerated(EnumType.STRING)
    private PositionName position;
}
