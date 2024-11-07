package com.hql.todo.dao;

import com.hql.entities.Coach;
import com.hql.todo.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.util.List;

public class CoachDAO extends BaseDAO<Coach> {

    private final EntityManagerFactory FACTORY;

    public CoachDAO(EntityManagerFactory FACTORY) {
        super(Coach.class);
        this.FACTORY = FACTORY;
    }

    public List<String> finaAllSecondNamesByTeam(Long teamId) {
        try(EntityManager entityManager = FACTORY.createEntityManager()) {
            TypedQuery<String> query = entityManager.createQuery(
                    "SELECT c.secondName FROM Coach c WHERE c.team.id = :teamId", String.class
            );
            query.setParameter("teamId", teamId);
            return query.getResultList();
        }
    }

    public List<String> finaAllSecondNamesByTeamCriteria(Long teamId) {
        try(EntityManager entityManager = FACTORY.createEntityManager()) {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<String> cr = cb.createQuery(String.class);
            Root<Coach> root = cr.from(Coach.class);

            cr.select(root.get("secondName"))
                    .where(cb.equal(root.get("team").get("id"), teamId));
            TypedQuery<String> query = entityManager.createQuery(cr);
            return query.getResultList();
        }
    }
}
