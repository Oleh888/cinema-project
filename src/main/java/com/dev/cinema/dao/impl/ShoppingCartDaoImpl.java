package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.ShoppingCartDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.util.HibernateUtil;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    private static final Logger LOGGER = Logger.getLogger(ShoppingCartDaoImpl.class);

    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(shoppingCart);
            transaction.commit();
            LOGGER.info(String.format("Shopping cart with id: %d was added to the DB.",
                    shoppingCart.getId()));
            return shoppingCart;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert shopping cart entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public ShoppingCart getByUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<ShoppingCart> criteriaQuery =
                    criteriaBuilder.createQuery(ShoppingCart.class);
            Root<ShoppingCart> root = criteriaQuery.from(ShoppingCart.class);
            Predicate predicateForUser = criteriaBuilder.equal(root.get("user"), user);
            criteriaQuery.where(predicateForUser);
            ShoppingCart shoppingCart = session.createQuery(criteriaQuery).uniqueResult();
            Hibernate.initialize(shoppingCart.getTickets());
            return shoppingCart;
        } catch (Exception e) {
            throw new DataProcessingException("Can't get shopping cart by user: " + user, e);
        }
    }

    @Override
    public void update(ShoppingCart shoppingCart) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(shoppingCart);
            transaction.commit();
            LOGGER.info(String.format("Shopping cart with id: %d was updated.",
                    shoppingCart.getId()));
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't update shopping cart entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
