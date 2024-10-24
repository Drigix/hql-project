package com.hql;

import com.hql.entities.Person;
import com.hql.entities.Player;
import com.hql.utils.DataLoader;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.Map;

public class Main {

    static final Map<String, String> props = new HashMap<>();

    static {
        props.put("jakarta.persistence.schema-generation.database.action", "drop-and-create");
    }
    private final static EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("footballPU", props);

    public static void main(String[] args) {
        DataLoader dataLoader = new DataLoader(FACTORY);
        dataLoader.loadTeams();
        dataLoader.loadStadiums();
        dataLoader.playTestMatch();
    }
}