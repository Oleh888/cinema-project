package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.UserDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.User;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends GenericDaoImp<User> implements UserDao {
    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);
    private final SessionFactory sessionFactory;

    public UserDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User add(User user) {
        user = super.add(user);
        LOGGER.info(String.format("User with email %s successfully added.", user.getEmail()));
        return user;
    }

    @Override
    public Optional<User> getByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery =
                    criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            Predicate predicateForEmail = criteriaBuilder.equal(root.get("email"), email);
            criteriaQuery.where(predicateForEmail);
            return Optional.ofNullable(session.createQuery(criteriaQuery).uniqueResult());
        } catch (Exception e) {
            throw new DataProcessingException("Can't get user with email " + email, e);
        }
    }

    @Override
    public User getById(Long id) {
        return super.getById(id, User.class);
    }
}
