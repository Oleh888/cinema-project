package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.RoleDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.Role;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends GenericDaoImp<Role> implements RoleDao {
    private static final Logger LOGGER = Logger.getLogger(RoleDaoImpl.class);
    private final SessionFactory sessionFactory;

    public RoleDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Role add(Role role) {
        role = super.add(role);
        LOGGER.info(String.format("Role with id - %s successfully added.", role.getId()));
        return role;
    }

    @Override
    public Role getById(Long id) {
        return super.getById(id, Role.class);
    }

    @Override
    public Role getRoleByName(String roleName) {
        Role.RoleName roleNameEnum = Role.RoleName.valueOf(roleName);
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Role> criteriaQuery = criteriaBuilder.createQuery(Role.class);
            Root<Role> root = criteriaQuery.from(Role.class);
            Predicate predicate = criteriaBuilder.equal(root.get("roleName"), roleNameEnum);
            criteriaQuery.where(predicate);
            return session.createQuery(criteriaQuery).getSingleResult();
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving Role", e);
        }
    }
}
