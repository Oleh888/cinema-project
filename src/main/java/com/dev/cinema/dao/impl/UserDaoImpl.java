package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.UserDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.model.User;
import com.dev.cinema.util.HibernateUtil;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = Logger.getLogger(UserDao.class);

    @Override
    public User add(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Long userId = (Long) session.save(user);
            transaction.commit();
            user.setId(userId);
            LOGGER.info("user " + user.getName() + " was added to DB");
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert user entity", e);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery =
                    criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            Predicate predicateForEmail = criteriaBuilder.equal(root.get("email"), email);
            criteriaQuery.where(predicateForEmail);
            return Optional.of(session.createQuery(criteriaQuery).getSingleResult());
        } catch (Exception e) {
            throw new DataProcessingException(
                    "Can't get user with email " + email, e);
        }
    }
}
