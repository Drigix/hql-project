package com.hql.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_id_seq_generator")
    @SequenceGenerator(name = "person_id_seq_generator", sequenceName = "person_id_seq", allocationSize = 1)
    private Long id;

    private String fName;

    private String sName;
}
