package com.hql.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "team")
@Getter
@Setter
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_id_seq_generator")
    @SequenceGenerator(name = "team_id_seq_generator", sequenceName = "team_id_seq", allocationSize = 1)
    private Long id;

    private String country;

    @ManyToOne
    @JoinColumn(name="match_id", nullable=false)
    private Match match;

    @OneToMany(mappedBy="team")
    private List<Player> players;

    @OneToMany(mappedBy="team")
    private List<Coach> coaches;
}
