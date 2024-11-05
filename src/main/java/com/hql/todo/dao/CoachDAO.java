package com.hql.todo.dao;

import com.hql.entities.Coach;
import com.hql.todo.HibernateUtil;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.util.List;

public class CoachDAO extends BaseDAO<Coach> {

    public CoachDAO() {
        super(Coach.class);
    }

    public List<String> finaAllSecondNamesByTeam(Long teamId) {
        try (Session session = HibernateUtil.getSession()) {
            TypedQuery<String> query = session.createQuery(
                    "SELECT c.secondName FROM Coach c WHERE c.team.id = :teamId", String.class
            );
            query.setParameter("teamId", teamId);
            return query.getResultList();
        }
    }

    public List<String> finaAllSecondNamesByTeamCriteria(Long teamId) {
        try (Session session = HibernateUtil.getSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<String> cr = cb.createQuery(String.class);
            Root<Coach> root = cr.from(Coach.class);

            cr.select(root.get("secondName"))
                    .where(cb.equal(root.get("team").get("id"), teamId));
            TypedQuery<String> query = session.createQuery(cr);
            return query.getResultList();
        }
    }
}
