package com.hql.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.RecursiveTask;

@Entity
@Table(name = "STADIUMS", schema = "EURO")
public class Stadium implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stadium_id_seq_generator")
    @SequenceGenerator(name = "stadium_id_seq_generator", sequenceName = "stadium_id_seq", allocationSize = 1)
    private Long id;

    private String name;

    private String location;

    @OneToMany(mappedBy="stadium", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Match> matches;

    public Stadium() {
    }

    public Stadium(String name, String location) {
        this.name = name;
        this.location = location;
    }

    // region GETTERS and SETTERS

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Stadium setName(String name) {
        this.name = name;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public Stadium setLocation(String location) {
        this.location = location;
        return this;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public Stadium setMatches(List<Match> matches) {
        this.matches = matches;
        return this;
    }

    // endregion
}
