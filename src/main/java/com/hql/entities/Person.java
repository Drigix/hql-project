package com.hql.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "PERSONS")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_id_seq_generator")
    @SequenceGenerator(name = "person_id_seq_generator", sequenceName = "person_id_seq", allocationSize = 1)
    private Long id;

    private String firstName;

    private String secondName;

    public Person() {
    }

    public Person(String secondName, String firstName) {
        this.secondName = secondName;
        this.firstName = firstName;
    }

    // region GETTERS and SETTERS

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public Person setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getSecondName() {
        return secondName;
    }

    public Person setSecondName(String secondName) {
        this.secondName = secondName;
        return this;
    }

    // endregion
}
