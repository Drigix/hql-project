package com.hql.dao;

import com.hql.entity.Coach;
import com.hql.entity.Team;
import com.hql.utils.HibernateUtil;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;

import java.util.List;

public class TeamDAO extends BaseDAO<Team> {

    public TeamDAO() {
        super(Team.class);
    }

    List<Team> getAllTeamsByCoachAmount(Integer amount) {
        try (Session session = HibernateUtil.getSession()) {
            Query q = session.createQuery(
                    "SELECT t FROM Team t " +
                            "JOIN t.coaches c " +
                            "GROUP BY t " +
                            "HAVING COUNT(c) >= :amount", Team.class);

            q.setParameter("amount", amount);

            return q.getResultList();
        }
    }

    List<Team> getAllTeamsByCoachAmountCriteria(Integer amount) {
        try (Session session = HibernateUtil.getSession()) {

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Team> cr = cb.createQuery(Team.class);
            Root<Team> root = cr.from(Team.class);
            Expression<Long> ce = cb.count(root);

            Subquery<Long> subquery = cr.subquery(Long.class);
            Root<Team> teamSub = subquery.from(Team.class);
            Join<Team, Coach> coachJoin = teamSub.join("coaches");
            subquery.select(cb.count(coachJoin));
            subquery.groupBy(teamSub.get("id"));
            subquery.having(cb.ge(cb.count(coachJoin), amount));

            cr.where(cb.exists(subquery));

            TypedQuery<Team> tq = session.createQuery(cr);
            return tq.getResultList();
        }
    }
}
