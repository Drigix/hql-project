package com.hql.todo.dao;

import com.hql.entities.Coach;
import com.hql.entities.Match;
import com.hql.entities.PlayerMatchPosition;
import com.hql.entities.Team;
import com.hql.todo.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;

import java.util.List;
import java.util.Objects;

public class MatchDAO extends BaseDAO<Match>{

    private final EntityManagerFactory FACTORY;

    public MatchDAO(EntityManagerFactory FACTORY) {
        super(Match.class);
        this.FACTORY = FACTORY;
    }

    public List<Match> findAllWithAmountStrikers(Integer amount) {
        try(EntityManager entityManager = FACTORY.createEntityManager()) {
            TypedQuery<Match> query = entityManager.createQuery(
                    "SELECT m FROM Match m " +
                            "LEFT JOIN PlayerMatchPosition pmp ON m.id = pmp.match.id " +
                            "WHERE pmp.position = 'STRIKER' " +
                            "GROUP BY m.id " +
                            "HAVING COUNT(pmp) >= :amount",
                    Match.class
            );
            query.setParameter("amount", amount);
            return query.getResultList();
        }
    }

    public List<Match> findAllWithAmountStrikersCriteria(Integer amount) {
        try(EntityManager entityManager = FACTORY.createEntityManager()) {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Match> cr = cb.createQuery(Match.class);
            Root<Match> root = cr.from(Match.class);
            Expression<Long> ce = cb.count(root);

            Subquery<Long> subquery = cr.subquery(Long.class);
            Root<Match> matchRoot = subquery.from(Match.class);
            Join<Match, PlayerMatchPosition> playerMatchPositionJoin = matchRoot.join("playerMatchPositions");
            subquery.select(matchRoot.get("id"))
                    .where(
                            cb.equal(playerMatchPositionJoin.get("position"), "STRIKER")
                    )
                    .groupBy(matchRoot.get("id"))
                    .having(cb.ge(cb.count(playerMatchPositionJoin), amount));

            cr.select(root)
                    .where(cb.in(root.get("id")).value(subquery));

            TypedQuery<Match> tq = entityManager.createQuery(cr);
            return tq.getResultList();
        }
    }
}
