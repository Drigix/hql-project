package com.hql.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "stadium")
@SuperBuilder
@Getter
@Setter
public class Stadium {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stadium_id_seq_generator")
    @SequenceGenerator(name = "stadium_id_seq_generator", sequenceName = "stadium_id_seq", allocationSize = 1)
    private Long id;

    private String name;

    private String location;

    @OneToMany(mappedBy="stadium", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Match> matches;
}
