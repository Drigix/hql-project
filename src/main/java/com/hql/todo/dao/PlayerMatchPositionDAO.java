package com.hql.todo.dao;

import com.hql.entities.PlayerMatchPosition;
import jakarta.persistence.EntityManagerFactory;

public class PlayerMatchPositionDAO extends BaseDAO<PlayerMatchPosition> {

    public PlayerMatchPositionDAO(EntityManagerFactory FACTORY) {
        super(PlayerMatchPosition.class, FACTORY);
    }
}
