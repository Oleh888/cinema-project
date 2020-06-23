package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.GenericDao;
import com.dev.cinema.exceptions.DataProcessingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class GenericDaoImp<T> implements GenericDao<T> {
    private final SessionFactory sessionFactory;

    protected GenericDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public T add(T element) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(element);
            transaction.commit();
            return element;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert "
                    + element.getClass().getSimpleName(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    protected T getById(Long id, Class<T> clazz) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(clazz, id);
        } catch (Exception e) {
            throw new DataProcessingException("Can't get " + clazz.getSimpleName()
                    + " with id = " + id, e);
        }
    }
}
