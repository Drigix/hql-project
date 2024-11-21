package com.hql.todo.dao;

import com.hql.entities.*;
import com.hql.todo.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;

import java.util.List;
import java.util.Objects;

public class MatchDAO extends BaseDAO<Match> {

    public MatchDAO(EntityManagerFactory FACTORY) {
        super(Match.class, FACTORY);
    }

    public long countMatchPlayers(Integer matchId) {
        try (EntityManager entityManager = FACTORY.createEntityManager()) {
            TypedQuery<Integer> query = entityManager.createQuery(
                    "SELECT size(m.playerMatchPositions) FROM Match m " +
                            "WHERE :matchId = m.id",
                    Integer.class
            );
            query.setParameter("matchId", matchId);
            return query.getSingleResult();
        }
    }

    public long countMatchPlayersCriteria(Integer matchId) {
        try (EntityManager entityManager = FACTORY.createEntityManager()) {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);

            Root<Match> matchRoot = cq.from(Match.class);
            cq.select(cb.count(matchRoot.join("playerMatchPositions")))
                    .where(cb.equal(matchRoot.get("id"), matchId));

            TypedQuery<Long> query = entityManager.createQuery(cq);
            return query.getSingleResult();
        }
    }

    public List<Match> findAllWithAmountStrikers(Integer amount) {
        try (EntityManager entityManager = FACTORY.createEntityManager()) {
            TypedQuery<Match> query = entityManager.createQuery(
                    "SELECT m FROM Match m " +
                            "JOIN m.playerMatchPositions pmp " +
                            "WHERE pmp.position = 'STRIKER' " +
                            "GROUP BY m " +
                            "HAVING COUNT(pmp) >= :amount",
                    Match.class
            );
            query.setParameter("amount", amount);
            return query.getResultList();
        }
    }

    public List<Match> findAllWithAmountStrikersCriteria(Integer amount) {
        try (EntityManager entityManager = FACTORY.createEntityManager()) {
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
