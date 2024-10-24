package com.hql.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "player")
@SuperBuilder
@Getter
@Setter
public class Player extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_id_seq_generator")
    @SequenceGenerator(name = "player_id_seq_generator", sequenceName = "player_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="team_id", nullable=true)
    private Team team;

    @OneToMany(mappedBy="player", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PlayerMatchPosition> playerMatchPositions;
}
