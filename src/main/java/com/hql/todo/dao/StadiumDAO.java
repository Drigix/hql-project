package com.hql.todo.dao;

import com.hql.entities.Stadium;
import jakarta.persistence.EntityManagerFactory;

public class StadiumDAO extends BaseDAO<Stadium> {

    public StadiumDAO(EntityManagerFactory FACTORY) {
        super(Stadium.class, FACTORY);
    }
}
