package com.hql.todo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public abstract class BaseDAO<T> {
    private final Class<T> clazz;
    protected final EntityManagerFactory FACTORY;

    protected BaseDAO(Class<T> clazz, EntityManagerFactory FACTORY) {
        this.clazz = clazz;
        this.FACTORY = FACTORY;
    }

    public void save(T entity) {
        EntityTransaction transaction = null;
        try(EntityManager entityManager = FACTORY.createEntityManager()) {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public T getById(Long id) {
        try(EntityManager entityManager = FACTORY.createEntityManager()) {
            return entityManager.find(clazz, id);
        }
    }

    public List<T> getAll() {
        try(EntityManager entityManager = FACTORY.createEntityManager()) {
            return entityManager.createQuery("from " + clazz.getName(), clazz).getResultList();
        }
    }

    public void update(T entity) {
        EntityTransaction transaction = null;
        try(EntityManager entityManager = FACTORY.createEntityManager()) {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void delete(T entity) {
        EntityTransaction transaction = null;
        try(EntityManager entityManager = FACTORY.createEntityManager()) {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
