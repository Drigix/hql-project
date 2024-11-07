package com.hql.todo.dao;

import com.hql.entities.Player;
import jakarta.persistence.EntityManagerFactory;

public class PlayerDAO extends BaseDAO<Player>{

    public PlayerDAO(EntityManagerFactory FACTORY) {
        super(Player.class, FACTORY);
    }
}
