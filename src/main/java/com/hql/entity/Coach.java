package com.hql.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "coach")
@SuperBuilder
@Getter
@Setter
public class Coach extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coach_id_seq_generator")
    @SequenceGenerator(name = "coach_id_seq_generator", sequenceName = "coach_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="team_id", nullable=true)
    private Team team;
}
