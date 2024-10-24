package com.hql.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "match")
@SuperBuilder
@Getter
@Setter
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "match_id_seq_generator")
    @SequenceGenerator(name = "match_id_seq_generator", sequenceName = "match_id_seq", allocationSize = 1)
    private Long id;

    private LocalDateTime date;

    private String result;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="stadium_id", nullable=false)
    private Stadium stadium;

//    @OneToMany(mappedBy="match", fetch = FetchType.EAGER)
//    private List<Team> teams;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="team_id", nullable = false)
    private Team team;

    @OneToMany(mappedBy="match", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<PlayerMatchPosition> playerMatchPositions;
}

