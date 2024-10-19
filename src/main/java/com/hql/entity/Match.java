package com.hql.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "match")
@Getter
@Setter
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "match_id_seq_generator")
    @SequenceGenerator(name = "match_id_seq_generator", sequenceName = "match_id_seq", allocationSize = 1)
    private Long id;

    private LocalDateTime date;

    private String result;

    @ManyToOne
    @JoinColumn(name="stadium_id", nullable=false)
    private Stadium stadium;

    @OneToMany(mappedBy="match")
    private List<Team> teams;

    @OneToMany(mappedBy="match", cascade = CascadeType.REMOVE)
    private List<PlayerMatchPosition> playerMatchPositions;
}

